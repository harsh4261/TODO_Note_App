package com.example.todoapp.State

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.todoapp.data_Layer.database.Todo
import java.time.Instant
import java.util.Date

data class State(
    var todoList: List<Todo> = emptyList(),
    var id : MutableState<Int> = mutableStateOf(0),
    var title : MutableState<String> = mutableStateOf(""),
    var description : MutableState<String> = mutableStateOf(""),
    var createdAt : MutableState<Date> = mutableStateOf(Date.from(Instant.now())),
    var dateOfCreation : MutableState<Long> = mutableStateOf(0),

    )