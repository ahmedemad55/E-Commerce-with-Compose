package com.example.plusshoping.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.plusshoping.R

class ProductDetailsScreen: Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current



    data class Product(
        val name: String,
        val image: Int,
        var price: Double = 0.0,
        var isFavorite: Boolean = false,
        var inCart: Boolean = false,

        )

    val allProducts = listOf(
        Product("Drinks", R.drawable.onboarding_photo2, 15.0, false, false),
        Product("Desserts", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Berger", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Rab", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Meet", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Choose", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Boos", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Done", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Well", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Yaa", R.drawable.onboarding_photo3, 20.0, false, false),
        Product("Moon", R.drawable.onboarding_photo3, 20.0, false, false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(id = allProducts[1].image),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Name, Rating, and Price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = allProducts[1].name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$"+ allProducts[1].price ,
                fontWeight = FontWeight.Bold
            )


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFD700)
            )
            Text(
                text = "4.6 (102k Reviews)",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 2.dp)
            )
            Text(
                text = "$80.99",
                textDecoration = TextDecoration.LineThrough,
                color = Color.Gray
            )

        }


        Spacer(modifier = Modifier.height(16.dp))

        // Description Section
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry...",
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Total Price and Add to Cart Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Price",
            )
            Text(
                text = "$83.97",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.appColor)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Add To Cart")
        }
    }
    }
}


