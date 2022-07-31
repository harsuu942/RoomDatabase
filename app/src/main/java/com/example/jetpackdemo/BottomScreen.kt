package com.example.jetpackdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import java.util.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Under Process",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        
    }
}

@Composable
fun AddUserScreen(userViewModel: UserViewModel) {
    val userList = userViewModel.fetchAllUsers().observeAsState(arrayListOf())


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)) {
        Row(modifier = Modifier
            .fillMaxSize(fraction = 1f)
            .background(color = Color.White)
            .align(Alignment.TopEnd)) {
            LazyColumn(content = {
                items(
                    items = userList.value,
                    itemContent = {
                        Card(
                            content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 16.dp),
                                    content = {
                                        Box(
                                            content = {
                                                Text(
                                                    text = it.name,
                                                    color = Color.Black,
                                                )
                                            }
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
            })
        }
        Row(modifier = Modifier
            .size(width = 100.dp, height = 80.dp)
            .align(Alignment.BottomEnd)) {
            ExtendedFloatingActionButton(text = {
                Text(text = "User", color = Color.White)
            }, onClick = {
                val name = UUID.randomUUID().toString()
                userViewModel.insertUser(
                        UserModel(
                          name = name,
                          gender = "Male",
                          emailId = "abc@gmail.com"
                        ),
                )
            })
        }
    }
}


@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun JobScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Jobs Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


