package com.duck.githubusercontent.ui.userscontent

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duck.githubusercontent.ui.Screen
import com.duck.githubusercontent.data.remote.objects.UserContent
import com.duck.githubusercontent.ui.Emtpy
import com.duck.githubusercontent.ui.NoData
import com.duck.githubusercontent.ui.theme.AppTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UsersContentScreen(
    viewModel: UsersContentViewModel = hiltViewModel(),
    navController: NavHostController
) {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text("Users Content") }
                    )
                }
            ) {
                // here you can add grid instead of list
                UsersContentList(
                    viewModel.uiState.items,
                    navController,
                    viewModel.uiState.isLoading
                )
                viewModel.uiState.errorMessage?.let {
                    scope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar(it)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersContentList(list: List<UserContent>, navController: NavHostController, isLoading: Boolean) {
    if (list.isEmpty()) {
        if (isLoading) {
            Emtpy()
        } else {
            NoData()
        }
    } else {
        LazyColumn {
            items(list.size) {
                UserContentCard(userContent = list[it], navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserContentCard(userContent: UserContent, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { navController.navigate(Screen.UserContent.createRoute(id = userContent.id)) }
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = userContent.fullName(),
                style = MaterialTheme.typography.h6
            )
        }
    }
}