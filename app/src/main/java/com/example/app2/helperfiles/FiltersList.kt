package com.example.app2.helperfiles

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Filters(val name: String)

val allFilters = listOf(
    Filters("smoking"),
    Filters("animals"),
    Filters("luggage"),
    Filters("test"),
)

@Composable
fun FilterList(allFilters: List<Filters>) : HashMap<String, String?> {
    val filterHashMap: HashMap<String, String?> = HashMap<String, String?>()
    val decision = remember { mutableStateOf("") }

    LazyColumn(
    ) {
        items(allFilters) { filter ->
            decision.value = FilterRow(filter)
            filterHashMap[filter.name] = decision.value
        }
    }
    return filterHashMap
}

@Composable
fun FilterRow(filter: Filters) : String{
    val decision = remember { mutableStateOf("") }
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
                filter.name.replaceFirstChar { it.uppercase() },
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight  = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            decision.value = CheckBoxDemo()
        }
    }
    return decision.value
}