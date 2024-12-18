package com.example.todoapp.data_Layer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Todo_Table")
data class Todo(
    @PrimaryKey(autoGenerate = true)var id : Int=0,
    var title : String,
    var description : String? = null,
    var createdAt : Date,
    var dateOfCreation : Long,
)