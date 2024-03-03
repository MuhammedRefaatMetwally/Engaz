package com.example.engaz.features.home.view.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral500
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.features.home.view.utils.BottomNavPage

@Composable
fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    pages : List<BottomNavPage> = emptyList(),
    index : Int = 0,
    onChange : (Int) -> Unit = {}
){
    BottomAppBar(
                actions = {
                    pages.forEachIndexed { thisIndex, item ->
                        val color = remember { Animatable(if(thisIndex == index) Primary else Color.Transparent) }

                        LaunchedEffect(index) {
                            if(thisIndex == index){
                                color.animateTo(Primary, animationSpec = tween(500))
                            }else {
                                color.animateTo(Color.Gray, animationSpec = tween(500))

                            }
                        }
                        if (thisIndex == 0){
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    onChange(thisIndex)
                                }
                            ,
                            painter = painterResource(
                                id = item.icon
                            ),
                            contentDescription = null,
                            tint = if(thisIndex == index) Primary else Neutral500
                        )
                        Spacer(modifier = Modifier.width(56.dp))
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = colorResource(id = R.color.primary_color),
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(modifier = Modifier
                            .size(32.dp)
                            , painter = painterResource(id = R.drawable.ic_qr_code),contentDescription =  "Localized description", tint = Color.White)
                    }
                }
            )
}