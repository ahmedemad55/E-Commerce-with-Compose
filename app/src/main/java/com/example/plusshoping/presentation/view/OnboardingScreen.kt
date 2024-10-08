package com.example.plusshoping.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.plusshoping.R
import com.example.plusshoping.data.model.OnboardingPage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


class OnboardingScreen:Screen {
    @Composable
    override fun Content() {
        OnboardingContent()


    }
    @Composable
    fun OnboardingContent() {
        val onboardingPages = listOf(
            OnboardingPage(
                title = "Welcome to Plus Shopping",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s,",
                imageResId = R.drawable.onboarding_photo1

            ),
            OnboardingPage(
                "Stay Organized",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s,",
                R.drawable.onboarding_photo2
            ),
            OnboardingPage(
                "Get Started",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s,",
                R.drawable.onboarding_photo3
            )
        )

        val pagerState = rememberPagerState(3)
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))


            // Pager for onboarding pages
            HorizontalPager(
                count = onboardingPages.size,
                state = pagerState
            ) { page ->
                OnboardingPageScreen(onboardingPages[page])
            }


            // Page indicator and next button
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.appColor)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Next", color = Color.White)
            }
            Row {
                // Dots indicator
                DotsIndicator(
                    totalDots = onboardingPages.size,
                    selectedIndex = pagerState.currentPage,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    if (pagerState.currentPage != 2) {
                        TextButton(onClick = {
                            scope.launch {
                                pagerState.scrollToPage(2)
                            }

                        }) {
                            Text(
                                "Skip", color = colorResource(id = R.color.appColor),
                                fontWeight = FontWeight.Normal, fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                    } else {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }

            }



            Spacer(modifier = Modifier.height(20.dp))
        }
    }
        @Composable
        fun OnboardingPageScreen(page: OnboardingPage) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(page.imageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(64.dp))

                Text(
                    text = page.title,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(
                        fontSize = 27.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = page.description,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = colorResource(id = R.color.appColor),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

            }
        }





    @Composable
    fun DotsIndicator(
        totalDots: Int,
        selectedIndex: Int,
        modifier: Modifier = Modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            for (i in 0 until totalDots) {
                Box(
                    modifier = Modifier
                        .size(if (i == selectedIndex) 12.dp else 8.dp)
                        .padding(4.dp)
                        .background(
                            color = if (i == selectedIndex) Color.Blue else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }
    }

    }







/*
@Composable
fun OnboardingScreen(
    page: OnboardingPage,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Image
        Image(
            painter = painterResource(id = page.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        )

        // Title
        Text(
            text = page.title,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Description
        Text(
            text = page.description,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        // Button
        Button(
            onClick = { onNextClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = page.buttonText)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPager(onFinish: () -> Unit) {
    val pagerState = rememberPagerState()

    // Page list containing the 3 onboarding pages
    val pages = onboardingPages

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            OnboardingScreen(
                page = pages[pageIndex],
                onNextClicked = {
                    if (pageIndex == pages.size - 1) {
                        onFinish()
                    } else {
                        // Move to next page
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.animateScrollToPage(pageIndex + 1)
                        }
                    }
                }
            )
        }

        // Dots indicator
        DotsIndicator(
            totalDots = pages.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}



 */



