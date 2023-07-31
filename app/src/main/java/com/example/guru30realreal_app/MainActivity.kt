package com.example.guru30realreal_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import com.example.guru30realreal_app.auth.IntroActivity
import com.example.guru30realreal_app.R
import com.example.guru30realreal_app.board.BoardListLVAdapter
import com.example.guru30realreal_app.board.BoardModel
import com.example.guru30realreal_app.board.BoardWriteActivity
import com.example.guru30realreal_app.utils.FBRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val boardDataList = mutableListOf<BoardModel>()
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        //val binding = inflate(Inflater,R.layout.activity_main,null,false)

        val boardList = mutableListOf<BoardModel>()
        boardList.add(BoardModel())

        val boardRVAdapter = BoardListLVAdapter(boardDataList)
        //binding.boardListView.adapter = boardRVAdapter

        auth = Firebase.auth


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.logoutBtn).setOnClickListener {

            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
        getFBBoardData()
    }
    private fun getFBBoardData(){
        val postListener = object:ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d(TAG,dataModel.toString())
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                }
                Log.d(TAG,boardDataList.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG,"loadPost:onCancelled",databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }
    fun onImageViewClicked(view: View) {

        val intent = Intent(this, BoardWriteActivity::class.java)
        startActivity(intent)
    }
}