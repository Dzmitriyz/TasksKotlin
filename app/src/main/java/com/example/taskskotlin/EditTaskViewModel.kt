package com.example.taskskotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val dao: TaskDAO): ViewModel() {
    val task = dao.get(taskId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigaToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateTask(){
        viewModelScope.launch {
            dao.update(task.value!!)
            _navigateToList.value = true
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigateToList.value = true
        }
    }
    fun onNavigatedToList(){
        _navigateToList.value = false 
    }
}