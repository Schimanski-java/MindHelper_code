package com.example.mindhelper

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskFragmentViewModel : ViewModel() {

    private val firstTask: MutableList<String> = mutableListOf("test1", "test2")
    private val secondTask: MutableList<String> = mutableListOf("test3", "test4")
    private val thirdTask: MutableList<String> = mutableListOf("test5", "test6")

    private val _currentTaskOne = MutableLiveData<Boolean>()
    val currentTaskOne: LiveData<Boolean>
        get() = _currentTaskOne

    private lateinit var _currentFirstTask: String
    val currentFirstTask: String
        get() = _currentFirstTask
    private lateinit var _currentSecTask: String
    val currentSecTask: String
        get() = _currentSecTask
    private lateinit var _currentThirdTask: String
    val currentThirdTask: String
        get() = _currentThirdTask



    fun changeTasks() {
                _currentFirstTask = firstTask.random()
                _currentSecTask = secondTask.random()
                _currentThirdTask = thirdTask.random()
        }




}