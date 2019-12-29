package com.example.myapplication.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Task
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TasksRepository {
    private val tasksService = Api.tasksService
    private val coroutineScope = MainScope()

    fun getTasks(): LiveData<List<Task>?> {
        val tasks = MutableLiveData<List<Task>?>()
        coroutineScope.launch { tasks.postValue(loadTasks()) }
        return tasks
    }

    fun deleteTask(id : String): Unit {
        coroutineScope.launch{tasksService.deleteTask(id)}
    }

    private suspend fun loadTasks(): List<Task>? {
        val tasksResponse = tasksService.getTasks()
        Log.e("loadTasks", tasksResponse.toString())
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }
}