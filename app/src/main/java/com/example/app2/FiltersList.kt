package com.example.app2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Filters(val name: String)

val filterss = listOf(
    Filters("Smoking"),
    Filters("Animals"),
    Filters("Luggage"),
)

@Composable
fun FilterList(filterss: List<Filters>) {
    LazyColumn(
    ) {
        items(filterss) { filter ->
            FilterRow(filter)
        }
    }
}

@Composable
fun FilterRow(filter: Filters) {
    Card(
        modifier = Modifier
            .width(340.dp),
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Row(modifier = Modifier
            .background((Color(0xFFF1F1F1))),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                filter.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight  = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            CheckBoxDemo()
        }
    }
}