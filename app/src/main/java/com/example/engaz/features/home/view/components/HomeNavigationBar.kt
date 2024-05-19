package com.example.engaz.features.home.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Neutral500
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.features.home.view.utils.BottomNavPage

@Composable
fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    pages: List<BottomNavPage> = emptyList(),
    index: Int = 0,
    onFBAClick: () -> Unit = {},
    onChange: (Int) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    BottomAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp),
        actions = {
            pages.forEachIndexed { thisIndex, item ->
                val iconSize = calculateIconSize(screenWidth) // Implement logic to calculate icon size
                val spacing = calculateSpacing(screenWidth) // Implement logic to calculate spacing

                Spacer(modifier = Modifier.width(spacing))

                Icon(
                    modifier = Modifier.size(iconSize).clickable {
                        onChange(thisIndex)
                    },
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = if (thisIndex == index) Primary else Neutral500
                )

            }
        },
        floatingActionButton = {
            val fabSize = calculateFabSize(screenWidth) // Implement logic to calculate FAB size

            FloatingActionButton(
                onClick = { onFBAClick() },
                modifier = Modifier.size(fabSize),
                containerColor = colorResource(id = R.color.primary_color),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_qr_code),
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        },
    )
}

private fun calculateIconSize(screenWidth: Dp): Dp {
    // Example: Set icon size to 10% of the screen width
    return screenWidth * 0.1f
}


private fun calculateSpacing(screenWidth: Dp): Dp {
    // Example: Set spacing to 16dp
    return 22.dp
}


private fun calculateFabSize(screenWidth: Dp): Dp {
    // Example: Set FAB size to 15% of the screen width
    return screenWidth * 0.15f
}

