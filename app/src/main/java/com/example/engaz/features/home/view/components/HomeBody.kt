package com.example.engaz.features.home.view.components

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.shimmerEffect

@Composable
fun HomeBody(
    isLoading: Boolean = false,
    assistantImage: String,
    assistantName: String,
    onInfoClick: (Int) -> Unit = {}
) {
    val context: Context = LocalContext.current
    val infoTitles = listOf(
        stringResource(R.string.info_about_service),
        stringResource(R.string.more_info_about_car),
        stringResource(R.string.transfer_car_ownership),
        stringResource(R.string.renew_car_license_ar),
        stringResource(R.string.revise_car_requirments)
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .height(240.dp)
                    .width(240.dp)
                    .clip(CircleShape)
                    .shadow(
                        elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                        shape = CircleShape,
                        clip = false,
                        ambientColor = DefaultShadowColor,
                        spotColor = DefaultShadowColor,
                    )
                    .shimmerEffect(),
            )
        } else {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                painter = rememberImagePainter(data = R.drawable.ic_main_picture, builder = {
                    transformations() // Apply transformations if needed
                    placeholder(R.drawable.photo) // Placeholder resource while loading
                    error(R.drawable.photo_error) // Error resource if loading fails
                }),
                contentDescription = null, //
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {

        } else {
            Text(
                text = stringResource(R.string.transfer_ownership_ar),
                style = TextStyle(
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.Black,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.transfer_ownership_description),
                style = TextStyle(
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W500,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.Black,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.SpaceEvenly,
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                this.items(infoTitles.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                            .aspectRatio(1f) // Maintain a square aspect ratio
                            .clickable { onInfoClick(index) },
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.primary_color2)
                        )
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 24.dp),
                            text = infoTitles[index],
                            fontFamily = Cairo,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W700,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}