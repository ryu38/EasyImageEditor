package com.example.easyimageeditor.ui.base

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigation(
    navItems: List<NavItems>,
    selectedItemIndex: Int = 0,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        navItems.forEachIndexed { index, navItem ->
            BottomNavigationItem(
                label = { Text(navItem.label) },
                icon = { Icon(navItem.icon, contentDescription = navItem.label) },
                selected = selectedItemIndex == index,
                onClick = {
                    navItem.onClick()
                }
            )
        }
    }
}

data class NavItems(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)