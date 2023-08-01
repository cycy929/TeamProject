package com.example.guru30realreal_app.utils
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FBRef {
    companion object {

        val database = Firebase.database

        val boardRef = database.getReference("board")


    }
}
