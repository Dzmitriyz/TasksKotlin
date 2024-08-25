package com.example.taskskotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.map

class TaskViewModel(val dao:TaskDAO): ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()
    val tasksString = tasks.map { tasks ->
        formatTasks(tasks)

    }
    fun addTask(){
        viewModelScope.launch {
        val task = Task()
        task.taskName = newTaskName
        dao.insert(task)
        }
    }

    fun formatTasks(tasks: List<Task>):String{
        return tasks.fold(""){
            str, item -> str + '\n' +formatTask(item)
        }
    }
    fun formatTask(task: Task): String{
        var str = "ID ${task.taskId}"
        str +='\n' + "Name: ${task.taskName}"
        str  +='\n' + "Comleter: ${task.taskDone}"+'\n'
        return str
    }
}
