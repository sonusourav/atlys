package com.sonusourav.atlys.presentation.trending.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController) {
    Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.CenterStart) {
        SearchBar(navController = navController)
    }
}
