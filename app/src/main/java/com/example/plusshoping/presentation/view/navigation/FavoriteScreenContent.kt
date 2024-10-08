package com.example.plusshoping.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


@Composable
fun FavoriteScreenContent(){
    val context = LocalContext.current
    val errorMessage by remember { mutableStateOf<String?>(null) }
    val navigator = LocalNavigator.currentOrThrow


}