package com.example.todoapp.data_Layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = true
    )
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun todoDao() : TodoDao

}