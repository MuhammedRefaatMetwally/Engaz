package com.example.engaz.core.views.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.CustomPageIndicator
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.core.views.pages.OnBoardingPage
import com.example.engaz.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun OnBoardingScreen(
    onSkipClick : (DestinationsNavigator) -> Unit = {},
    onNextClick : (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?
    ) {
    val context: Context = LocalContext.current

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = if( isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
            ,
        ) { HorizontalPager(
                  state = pagerState,
                  modifier = Modifier.fillMaxSize(),) { page ->
                  when (page) {
                      0 -> OnBoardingPage(
                          image = R.drawable.onboarding_1,
                          label = stringResource(R.string.onboarding_title_1),
                          subText = stringResource(R.string.onboarding_subtext1),
                      )

                      1 -> OnBoardingPage(
                          image = R.drawable.onboarding_2,
                          label = stringResource(R.string.onboarding_title2),
                          subText = stringResource(R.string.onboarding_subtext2),
                      )

                      2 -> OnBoardingPage(
                          image = R.drawable.onboarding_3,
                          label = stringResource(R.string.onboarding_title3),
                          subText = stringResource(R.string.onboarding_subtext3),
                      )

                      else -> throw IllegalStateException("image not provided for page $page")
                  }
              }


            CustomPageIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 148.dp),
                totalPages = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                indicatorSize = 7.dp,
                color = colorResource(id = R.color.primary_color),
            )


            MainButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        if (pagerState.currentPage != 2) {
                            scope.launch {

                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            navigator?.let {
                                onNextClick(navigator)
                            }
                        }
                    },
                cardColor = colorResource(id = R.color.primary_color),
                borderColor = Color.Transparent
            ){
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                ){


                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text =if(pagerState.currentPage == 0 || pagerState.currentPage == 1) stringResource(R.string.onboarding_next_ar) else stringResource(
                            R.string.start_ar
                        ),
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = Color.White ,
                            fontSize = 20.sp
                        )
                    )
                }
            }

            if(pagerState.currentPage == 0)
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 24.dp , start = 16.dp)
                    .wrapContentHeight()
                    .clickable {

                        navigator?.let {
                            onSkipClick(navigator)
                        }
                    },
                text = stringResource(R.string.skip_ar),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Color.Black,
                    fontSize = 22.sp)
            )
        }


    }

}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    MarketAppTheme {
        OnBoardingScreen(
            navigator = null
        )

    }
}