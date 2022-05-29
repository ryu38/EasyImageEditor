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
    initIndex: Int = 0,
) {
    var selectedItemId by rememberSaveable { mutableStateOf(initIndex) }
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        navItems.forEachIndexed { index, navItem ->
            BottomNavigationItem(
                label = { Text(navItem.label) },
                icon = { Icon(navItem.icon, contentDescription = navItem.label) },
                selected = selectedItemId == index,
                onClick = {
                    navItem.onClick()
                    selectedItemId = index
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