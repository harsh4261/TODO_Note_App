package com.example.todoapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.UI_Layer.AddTask
import com.example.todoapp.UI_Layer.HomePage
import com.example.todoapp.ViewModel.TodoViewModel
import kotlinx.serialization.Serializable

@Composable
fun Nav1(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    val stateValues  by viewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
         composable<Home> {
             HomePage(stateValues,navController,viewModel)
         }
        composable<AddTask> {
            AddTask(stateValues,navController,viewModel)
        }
    }
}

@Serializable
object Home

@Serializable
object AddTask