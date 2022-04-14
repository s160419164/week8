package com.ubaya.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ubaya.todo.model.Todo
import com.ubaya.todo.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application:  Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addTodo(list: List<Todo>) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), TodoDatabase::class.java,
                "newtododb").build()
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}