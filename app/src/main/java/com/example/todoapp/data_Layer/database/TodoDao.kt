package com.example.todoapp.data_Layer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo_Table ORDER BY createdAt DESC")
    fun getTodoSortedByLatest(): Flow<List<Todo>>

    @Query("SELECT * FROM Todo_Table ORDER BY createdAt ASC")
    fun getTodoSortedByOldest(): Flow<List<Todo>>

    @Upsert
    suspend fun addTodoTask(todo :Todo)

    @Delete
    suspend fun deleteTodoTask(todo: Todo)
}