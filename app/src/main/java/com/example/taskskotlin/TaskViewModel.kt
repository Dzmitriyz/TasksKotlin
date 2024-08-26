package com.example.taskskotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.map

class TaskViewModel(val dao:TaskDAO): ViewModel() {
    var newTaskName = ""

    val tasks = dao.getAll()


    fun addTask(){
        viewModelScope.launch {
        val task = Task()
        task.taskName = newTaskName
        dao.insert(task)
        }
    }


}
