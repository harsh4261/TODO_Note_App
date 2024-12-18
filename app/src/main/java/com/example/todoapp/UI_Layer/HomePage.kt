package com.example.todoapp.UI_Layer

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.Navigation.AddTask
import com.example.todoapp.State.State
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.data_Layer.database.Todo
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(stateValues: State, navController: NavHostController, viewModel: TodoViewModel) {


    var scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(

        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo Note App",
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = {
                    stateValues.title.value = ""
                    stateValues.description.value = ""
                    navController.navigate(AddTask)
                },
                modifier = Modifier
                    .size(70.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),

                ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add Button",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,

        ) { innerPadding ->

        if (stateValues.todoList.isNotEmpty()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                items(stateValues.todoList) { item ->

                    BoxContent(
                        item = item,
                        deleteContent = {
                            stateValues.id.value = item.id
                            stateValues.title.value = item.title
                            stateValues.description.value = item.description.toString()
                            stateValues.createdAt.value = item.createdAt
                            stateValues.dateOfCreation.value = item.dateOfCreation
                            viewModel.deleteTodoTask()
                        },
                        Update = {
                            stateValues.id.value = item.id
                            stateValues.title.value = item.title
                            stateValues.description.value = item.description.toString()
                            stateValues.createdAt.value = item.createdAt
                            stateValues.dateOfCreation.value = item.dateOfCreation
                            navController.navigate(AddTask)
                        }
                    )

                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No items yet",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                    )
                )

            }

        }
    }


}


@Composable
fun BoxContent(item: Todo, deleteContent: () -> Unit, Update: () -> Unit) {

    val lightColorList = listOf(
        Color(0xFFFFC0CB), // Light Pink
        Color(0xFFADD8E6), // Light Blue
        Color(0xFFFFE4B5), // Light Goldenrod Yellow
        Color(0xFFFFDAB9), // Peach Puff
        Color(0xFFE6E6FA), // Lavender
        Color(0xFFFFB6C1), // Light Salmon
        Color(0xFFB0E0E6), // Powder Blue
        Color(0xFFFFEFD5), // Papaya Whip
        Color(0xFFF5F5DC)  // Beige
    )
    val randomIndex = (lightColorList.indices).random()
    val randomColor by remember {
        mutableStateOf(lightColorList[randomIndex])
    }

    var showDescription by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                showDescription = !showDescription
            }
            .background(randomColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                )
                Text(
                    text = SimpleDateFormat("E, d MMM yyyy").format(item.createdAt),
                    fontSize = 12.sp,
                    color = Color.Black
                )


            }
            IconButton(onClick = deleteContent) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Green
                )
            }
        }
        if (showDescription) {
            Column {

                Text(
                    text = item.description ?: "",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(Modifier.height(10.dp))

                OutlinedButton(
                    onClick = Update,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "Edit",
                        color = Color.Black
                    )
                }
                Spacer(Modifier.height(10.dp))

            }
        }
    }

}