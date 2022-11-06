package com.example.app2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun ShowDatePicker(
    messageError: String,
    errorState: Boolean
): String{
    val context = LocalContext.current

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val date = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            if(dayOfMonth < 10 && (month+1) < 10) {
                date.value = "0$dayOfMonth/0${month + 1}/$year"
            }
            else if(dayOfMonth < 10){
                date.value = "0$dayOfMonth/${month + 1}/$year"
            }
            else if(month < 10){
                date.value = "$dayOfMonth/0${month + 1}/$year"
            }
            else{
                date.value = "$dayOfMonth/${month + 1}/$year"
            }
        }, year, month, day
    )
    Button(onClick = { datePickerDialog.show() },
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252)),
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)) {

        Icon(
            Icons.Default.DateRange,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Calendar",
            tint = Color.White
        )
    }

    Spacer(Modifier.width(3.dp))

    PrettyBar(
        modifier = Modifier
            .height(90.dp)
            .width(128.dp)
            .padding(vertical = 0.dp),

        type = "Date",
        activeVariable = date.value,
        onVarChange = {
            date.value = it
        },
        errorMessage = messageError,
        showError = !errorState
    )
    return date.value
}
