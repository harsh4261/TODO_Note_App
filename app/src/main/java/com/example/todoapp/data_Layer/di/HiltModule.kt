package com.example.todoapp.data_Layer.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data_Layer.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule  {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : TodoDatabase {
        return  Room.databaseBuilder(
            context = application.applicationContext,
            klass = TodoDatabase::class.java,
            name = "todo_db"
        ).build()

    }
}