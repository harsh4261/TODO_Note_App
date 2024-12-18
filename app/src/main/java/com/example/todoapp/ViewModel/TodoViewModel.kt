package com.example.todoapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.State.State
import com.example.todoapp.data_Layer.database.Todo
import com.example.todoapp.data_Layer.database.TodoDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(var database: TodoDatabase) : ViewModel() {

    //<-------------getting the all todo task from the database---------->
    var isSortedByLatest =  MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private  var todoList = isSortedByLatest.flatMapLatest {
        if (it){
            database.todoDao().getTodoSortedByLatest()
        }else{
            database.todoDao().getTodoSortedByOldest()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    var _state = MutableStateFlow(State())
    var state = combine(_state,todoList,isSortedByLatest){
        _state,_todoList,isSortedByLatest ->
        _state.copy(
            todoList = _todoList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = State()
    )

    //<----------function for add and update the todo task---------->
    fun addTodoTask(){

        var todo = Todo(
            id = state.value.id.value,
            title = state.value.title.value,
            description = state.value.description.value,
            createdAt = Date.from(Instant.now()),
            dateOfCreation = System.currentTimeMillis()

        )
        viewModelScope.launch(Dispatchers.IO) {
            database.todoDao().addTodoTask(todo)
        }

//         state.value.id.value=0
//        state.value.title.value=""
//        state.value.description.value=""
//        state.value.createdAt.value= Date.from(Instant.now())
//        state.value.dateOfCreation.value=0

    }

    // Delete function

    fun deleteTodoTask(){
        var todo = Todo(
            id = state.value.id.value,
            title = state.value.title.value,
            description = state.value.description.value,
            createdAt =  state.value.createdAt.value,
            dateOfCreation =  state.value.dateOfCreation.value

        )
        viewModelScope.launch(Dispatchers.IO) {
            database.todoDao().deleteTodoTask(todo)
        }

        state.value.id.value=0
        state.value.title.value=""
        state.value.description.value=""
        state.value.createdAt.value= Date.from(Instant.now())
        state.value.dateOfCreation.value=0

    }





}