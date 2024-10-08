package com.example.plusshoping.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.plusshoping.R
import com.example.plusshoping.presentation.view.navigation.CartScreenContent
import com.example.plusshoping.presentation.view.navigation.CategoriesScreenContent
import com.example.plusshoping.presentation.view.navigation.FavoriteScreenContent
import com.example.plusshoping.presentation.view.navigation.HomeScreenContent
import com.example.plusshoping.presentation.view.navigation.ProfileScreenContent

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val errorMessage by remember { mutableStateOf<String?>(null) }
        val navigator = LocalNavigator.currentOrThrow
        val selectedIndex = remember { mutableIntStateOf(0) }
        val showBars = remember { mutableStateOf(true) }


            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { HomeTopAppBar() },
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(16.dp)),
                        backgroundColor = colorResource(id = R.color.secondaryColor)
                    ) {

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.home_icon),
                                    contentDescription = "Home Icon",
                                    tint = if (selectedIndex.intValue == 0) colorResource(id = R.color.appColor) else colorResource(
                                        id = R.color.navIconColor
                                    )

                                )
                            },
                            selected = false,
                            onClick = { selectedIndex.intValue = 0 },
                            label = {
                                Text(
                                    stringResource(id = R.string.home),
                                    fontSize = 10.sp,
                                    color = colorResource(id = R.color.appColor)
                                )
                            },
//                        selectedContentColor = colorResource(id = R.color.primaryColor),
//                        unselectedContentColor = Color.Gray
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.cart_icon),
                                    contentDescription = "Cart Icon",
                                    tint = if (selectedIndex.intValue == 1) colorResource(id = R.color.appColor) else colorResource(
                                        id = R.color.navIconColor
                                    )

                                )
                            },
                            selected = false,
                            onClick = { selectedIndex.intValue = 1 },
                            label = { Text(stringResource(id = R.string.cart), fontSize = 10.sp) },
//            selectedContentColor = colorResource(id = R.color.primaryColor),
//            unselectedContentColor = Color.Gray
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.categories_icon),
                                    contentDescription = "Categories Icon",
                                    tint = if (selectedIndex.intValue == 2) colorResource(id = R.color.appColor) else colorResource(
                                        id = R.color.navIconColor
                                    )

                                )
                            },
                            selected = false,
                            onClick = { selectedIndex.intValue = 2 },
                            label = {
                                Text(
                                    stringResource(id = R.string.categories),
                                    fontSize = 10.sp
                                )
                            },
//            selectedContentColor = colorResource(id = R.color.primaryColor),
//            unselectedContentColor = Color.Gray
                        )
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.favorite_icon),
                                    contentDescription = "Search Icon",
                                    tint = if (selectedIndex.intValue == 3) colorResource(id = R.color.appColor) else colorResource(
                                        id = R.color.navIconColor
                                    )

                                )
                            },
                            selected = false,
                            onClick = { selectedIndex.intValue = 3 },
                            label = {
                                Text(
                                    stringResource(id = R.string.wish_list),
                                    fontSize = 10.sp
                                )
                            },
//            selectedContentColor = colorResource(id = R.color.primaryColor),
//            unselectedContentColor = Color.Gray
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.profile_icon),
                                    contentDescription = "Profile Icon",
                                    tint = if (selectedIndex.intValue == 4) colorResource(id = R.color.appColor) else colorResource(
                                        id = R.color.navIconColor
                                    )

                                )
                            },
                            selected = false,
                            onClick = { selectedIndex.intValue = 4 },
                            label = {
                                Text(
                                    stringResource(id = R.string.profile),
                                    fontSize = 10.sp,
                                    color = Color.Black
                                )
                            },
//            selectedContentColor = colorResource(id = R.color.primaryColor),
//            unselectedContentColor = Color.Gray
                        )


                    }
                }
            ) { paddingValues ->
                // Screen content wrapped in a Column
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
//                    .verticalScroll(rememberScrollState())
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    when (selectedIndex.intValue) {
                        0 -> HomeScreenContent()
                        1 -> CartScreenContent()
                        2 -> CategoriesScreenContent()
                        3 -> FavoriteScreenContent()
                        4 -> ProfileScreenContent()
                    }
                }
            }



        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.appColor)
        ),

        title = {
            Image(
                modifier = Modifier
                    .padding(16.dp),
                painter = painterResource(R.drawable.home_plus),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        },
        actions = {
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_icon),
                    contentDescription = "Notification Icon",
                    tint = colorResource(id = R.color.secondaryColor)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* Handle drawer click */ }) {
                Icon(
//                    tint: Color = LocalContentColor.current,
                    painter = painterResource(id = R.drawable.drobdown_icon),
                    contentDescription = "Menu Icon",
                    tint = colorResource(id = R.color.secondaryColor)
                )
            }
        }
    )
}







