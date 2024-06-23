package com.latihan.githubuser.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.latihan.githubuser.R
import com.latihan.githubuser.models.UserModel
import com.latihan.githubuser.ui.components.UserFoundCard
import com.latihan.githubuser.viewmodel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Github User",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_github_64),
                        contentDescription = "Logo Github",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .padding(start = 20.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        HomeContent(modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun HomeContent(modifier: Modifier) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val userData by homeViewModel.userData.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    var searchData by remember {
        mutableStateOf("")
    }

    var debounceJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(searchData) {
        debounceJob?.cancel()
        debounceJob = launch {
            delay(500)  // Debounce time in milliseconds
            homeViewModel.getUsers(searchData)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = searchData,
            onValueChange = {searchData = it},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            placeholder = {
                Text(
                    text = "Search Users",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp
                )
            },
            prefix = {
                Icon(
                    painter = painterResource(id = R.drawable.search_24px), 
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        ShowDataUsers(isLoading = isLoading, userData = userData)
    }
}

@Composable
fun ShowDataUsers(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    userData: List<UserModel.Item?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (userData.isNotEmpty()) {
                Text(
                    text = "${userData.size} result",
                    modifier = Modifier.align(Alignment.Start)
                )
                LazyColumn(
                    modifier = Modifier
                ) {
                    items(userData.size) { index ->
                        UserFoundCard(userData = userData, index = index)
                    }
                }
            } else {
                Text(
                    text = "User Not Found",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
