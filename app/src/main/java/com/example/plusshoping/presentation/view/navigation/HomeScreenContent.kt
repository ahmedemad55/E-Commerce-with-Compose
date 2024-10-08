package com.example.plusshoping.presentation.view.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.plusshoping.R
import com.example.plusshoping.presentation.view.ProductDetailsScreen
import com.example.plusshoping.presentation.viewModel.HomeViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreenContent() {
    val context = LocalContext.current
    val errorMessage by remember { mutableStateOf<String?>(null) }
    val navigator = LocalNavigator.currentOrThrow
    val homeViewModel: HomeViewModel = hiltViewModel()
//    homeViewModel.loadHomeData(
//        { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
//        ,{ Toast.makeText(context, it, Toast.LENGTH_LONG).show() })


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(colorResource(id = R.color.secondaryColor))

    ) {

        SearchBar()

        BannerSection()
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesSection()
        Spacer(modifier = Modifier.height(16.dp))
        ProductSection()

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
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

@Composable
fun BannerSection() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val banners = homeViewModel.banners.collectAsState()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (banners.value?.data?.isNotEmpty() == true) {
            HorizontalPager(
                count = 7,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray),
                    elevation = 8.dp,

                    ) {
                    Image(
                        painter = rememberImagePainter(data = banners.value?.data?.get(page)?.image),
                        contentDescription = "Banner Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            DotsIndicator(
                totalDots = 7,
                selectedIndex = pagerState.currentPage
            )
        } else {
            Text("No banners available")
        }
    }
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(totalDots) { index ->
            val color =
                if (index == selectedIndex) colorResource(id = R.color.appColor) else colorResource(
                    id = R.color.navIconColor
                )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(16.dp)
            )
        }
    }
}


@Composable
fun CategoriesSection() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val allCategories =
        homeViewModel.allCategories.collectAsState().value?.data?.categories ?: emptyList()


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.categories),
                modifier = Modifier,
                fontWeight = FontWeight.Bold

            )

            Text(
                text = stringResource(id = R.string.viewAll),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.appColor)

            )

        }

        LazyRow(modifier = Modifier.fillMaxWidth()
            ,horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allCategories) { categories ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .size(75.dp),
                        elevation = 8.dp

                    ) {
                        AsyncImage(
                            model = categories.image,
                            contentDescription = "Category Image",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.logo_icon),
                            error = painterResource(id = R.drawable.logo_icon)
                        )


                    }
//                    Text(
//                        categories.name ?: "",
//                        modifier = Modifier.padding(top = 8.dp),
//                        textAlign = TextAlign.Center
//                    )
                    Text(
                        text = categories.name?.split(" ")?.joinToString(" ") { word ->
                            word.lowercase().replaceFirstChar { it.uppercase() }
                        } ?: "",
                        modifier = Modifier.padding(top = 8.dp).widthIn(max = 75.dp),
                        textAlign = TextAlign.Center,
                        maxLines = if ((categories.name ?: "").contains(" ")) 2 else 1,  // Conditional line breaks
                        overflow = TextOverflow.Ellipsis,  // Adds ellipsis if text overflows
                    )


                }


            }

        }


    }
}



@Composable
fun ProductSection() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val navigator = LocalNavigator.currentOrThrow

    val products = homeViewModel.products.collectAsState().value?.data?.products ?: emptyList()
    Column(modifier = Modifier.height(1000.dp)) {

        Text(
            text = stringResource(id = R.string.product),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold
        )

        Box(modifier = Modifier.fillMaxHeight()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    Card(
                        modifier = Modifier
                            .size(300.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        elevation = 16.dp
                    ) {

                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Box {
                                AsyncImage(
                                    model = product.image ?: "",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable { navigator.push(ProductDetailsScreen()) },
                                    contentScale = ContentScale.Fit


                                )
                                Spacer(Modifier.height(15.dp))

                                IconButton(
                                    onClick = { /* Handle like/unlike: */ product.in_favorites =
                                        !product.in_favorites
                                    },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(4.dp)
                                        .background(Color.White, shape = CircleShape)
                                        .size(24.dp)
                                ) {
                                    Icon(
                                        painter = if (product.in_favorites) painterResource(id = R.drawable.favorite_icon_red) else painterResource(
                                            id = R.drawable.favorite_icon
                                        ),
                                        contentDescription = "Favorite"
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            // Price
                            Text(
                                text = product.price.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            // Product name
                            Text(
                                text = product.name,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            // Seller and rating
                            Row(
                                modifier = Modifier.padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "From: (Nike)",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "5.0",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}


/* //Khedr Catergories
@Composable
fun GetCategories(onCategorySelected: (String) -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.findByCategory),
                modifier = Modifier.align(Alignment.TopStart),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,

                )
            )
            Text(
                text = stringResource(id = R.string.viewAll),
                modifier = Modifier.align(Alignment.TopEnd),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(modifier = Modifier) {
            items(categories?.data?.categories ?: emptyList()) {
                CategoryCard(it, onCategorySelected)
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category, onCategorySelected: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Card(
            shape = CircleShape,
            border = if (id.value == category.id) BorderStroke(
                1.dp,
                colorResource(id = R.color.appColor)
            ) else BorderStroke(0.dp, colorResource(id = R.color.black)),
            modifier = Modifier
                .size(80.dp)

        ) {
            Card(
                shape = CircleShape,
                border = if (id.value == category.id) BorderStroke(
                    3.dp,
                    colorResource(id = R.color.black)
                )
                else BorderStroke(3.dp, Color.LightGray),
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        onCategorySelected(category.id.toString())
                    }
            ) {
                AsyncImage(
                    model = category.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Text(
            text = category.name ?: "",
            modifier = Modifier.padding(top = 8.dp)
        )
    }

}


 */



