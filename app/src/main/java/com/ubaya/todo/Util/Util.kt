package com.ubaya.todo.Util

import android.content.Context
import androidx.room.Room
import com.ubaya.todo.model.TodoDatabase


val DB_NAME = "newtododb"
fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
        .build()
    return db
}

