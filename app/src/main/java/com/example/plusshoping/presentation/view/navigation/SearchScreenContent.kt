package com.example.plusshoping.presentation.view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.plusshoping.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreenContent(){
    val context = LocalContext.current
    val errorMessage by remember { mutableStateOf<String?>(null) }
    val navigator = LocalNavigator.currentOrThrow
    var search by remember { mutableStateOf("") }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.White)
                .background(Color.White),
            value = search,
            onValueChange = { search = it },
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search Icon",
//              tint = colorResource(id = R.color.primaryColor)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = "Search Icon",
//              tint = colorResource(id = R.color.primaryColor)
                )
            }, shape = RectangleShape, // Removes any border rounding
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent, // No border when unfocused
                focusedBorderColor = Color.Transparent    // No border when focused
            )
        )
    }



}