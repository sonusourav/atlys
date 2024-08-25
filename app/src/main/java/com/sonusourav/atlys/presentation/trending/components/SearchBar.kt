package com.sonusourav.atlys.presentation.trending.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sonusourav.atlys.presentation.Screen

@Composable
fun SearchBar(modifier: Modifier = Modifier, navController: NavController) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navController.navigate(Screen.SearchPageScreen.route)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_search_category_default),
                contentDescription = "search_icon",
                Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
                tint = Color.Gray
            )

            Text(
                text = "Search for a movie...",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Start
            )


        }
    }
}