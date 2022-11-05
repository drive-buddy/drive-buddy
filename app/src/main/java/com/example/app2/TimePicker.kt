package com.example.app2

import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ShowTimePicker(){
    val Context = LocalContext.current

    val calendar = Calendar.getInstance()
    val Hour = calendar[Calendar.HOUR_OF_DAY]
    val Minute = calendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("") }

    val timePickerDialog = TimePickerDialog(
        Context,
        {_, Hour : Int, Minute: Int ->
            time.value = "$Hour:$Minute"
        }, Hour, Minute, false
    )

    Button(onClick = { timePickerDialog.show() },
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(3.dp, Color(0xFFEE5252)),
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)) {

        Image(painter = painterResource(id = R.drawable.clock_icon),
            contentDescription = "Time icon",
            modifier = Modifier.size(20.dp))
    }
    Spacer(Modifier.width(3.dp))

    PrettyBar(
        modifier = Modifier
            .height(90.dp)
            .width(92.dp)
            .padding(vertical = 0.dp),

        type = "Time",
        activeVariable = time.value,
        onVarChange = {
            time.value = it
        }

    )
}