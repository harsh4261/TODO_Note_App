package com.example.todoapp.UI_Layer

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.Navigation.Home
import com.example.todoapp.State.State
import com.example.todoapp.ViewModel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(stateValues: State, navController: NavHostController, viewModel: TodoViewModel) {

    var scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )
    var focusRequester = remember {
        FocusRequester()
    }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Note",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Home)
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home Page",
                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                        )
                    }

                },
                actions = {
                    IconButton(
                        onClick = {
                            if (stateValues.title.value != "" && stateValues.description.value != "") {
                                stateValues.title.value = stateValues.title.value
                                stateValues.description.value = stateValues.description.value
                                stateValues.id.value = stateValues.id.value
                                stateValues.createdAt.value = stateValues.createdAt.value
                                stateValues.dateOfCreation.value = stateValues.dateOfCreation.value
                                viewModel.addTodoTask()
                                Toast.makeText(
                                    context,
                                    "Todo note is updated now !",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(Home)

                            } else {
                                Toast.makeText(
                                    context,
                                    "Title and Description cannot be empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                                focusRequester.requestFocus()
                            }

                        },
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Back to Home Page",
                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black

                        )
                    }
                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor =  if (isSystemInDarkTheme()) Color.Black else Color.White,
//                ),
                windowInsets = WindowInsets(
                    left = 0.dp,
                    top = 30.dp,
                    right = 0.dp,
                    bottom = 0.dp
                ),
                scrollBehavior = scrollBehavior

            )
        },
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Spacer(Modifier.height(40.dp))

            OutlinedTextField(
                value = stateValues.title.value,
                onValueChange = {
                    stateValues.title.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester = focusRequester),
                placeholder = {
                    Text(
                        text = "Enter Title",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                },
                label = {
                    Text(
                        text = "Title",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif

                        )
                    )
                },
                textStyle = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = 18.sp
                ),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    )

            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = stateValues.description.value,
                onValueChange = {
                    stateValues.description.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                placeholder = {
                    Text(
                        text = "Enter Description",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                },
                label = {
                    Text(
                        text = "Description",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    )

            )

        }

    }

}