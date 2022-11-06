package com.example.app2

import android.graphics.Color
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color.Companion.Black

class CheckBox {
}

@Composable
fun CheckBoxDemo(): String {
    val checkedState = remember { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        colors = CheckboxDefaults.colors(checkedColor = Black),
        onCheckedChange = { checkedState.value = it }
    )
    return checkedState.value.toString()
}