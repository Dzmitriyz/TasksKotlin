package com.example.taskskotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(val dao:TaskDAO): ViewModel() {
    var newTaskName = ""
    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    val tasks = dao.getAll()

    fun onTaskClicked(taskId: Long){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }
    fun addTask(){
        viewModelScope.launch {
        val task = Task()
        task.taskName = newTaskName
        dao.insert(task)
        }
    }
}
