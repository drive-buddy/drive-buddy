package com.example.app2

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.app2.helperfiles.PrettyBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ShowDatePicker(
    messageError: String,
    errorState: Boolean
): Long {
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
    val otherDate = remember { mutableStateOf("") }

            val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            if(dayOfMonth < 10 && (month+1) < 10) {
                date.value = "0$dayOfMonth-0${month + 1}-$year"
                otherDate.value = "$year-0${month + 1}-0$dayOfMonth"
            }
            else if(dayOfMonth < 10){
                date.value = "0$dayOfMonth-${month + 1}-$year"
                otherDate.value = "$year-${month + 1}-0$dayOfMonth"
            }
            else if(month < 10){
                date.value = "$dayOfMonth-0${month + 1}-$year"
                otherDate.value = "$year-0${month + 1}-$dayOfMonth"
            }
            else{
                date.value = "$dayOfMonth-${month + 1}-$year"
                otherDate.value = "$year-${month + 1}-$dayOfMonth"
            }
        }, year, month, day
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
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
                .padding(vertical = 0.dp),
            type = "The date of the journey",
            activeVariable = date.value,
            onVarChange = {
                date.value = it
            },
            readOnlyVal = true,
            errorMessage = messageError,
            showError = !errorState
        )
    }


    if (date.value != "")
    {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val text = otherDate.value.format(formatter)
        val parsedDate = LocalDate.parse(text, formatter)
        return (parsedDate.toEpochDay() * 24 * 60 * 60.toLong() - 2 * 60 * 60)
    }
    return 0
}