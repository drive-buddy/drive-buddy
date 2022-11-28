package com.example.app2.rides.schedule

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import com.example.app2.helperfiles.PrettyBar
import com.example.app2.rides.AvailableRides
import com.example.app2.ui.theme.App2_2Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ScheduleRide1: ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var destinationFrom by rememberSaveable { mutableStateOf("") }
                    var destinationTo by rememberSaveable { mutableStateOf("") }


                    var validateDestFrom by rememberSaveable { mutableStateOf(true) }
                    var validateDestTo by rememberSaveable { mutableStateOf(true) }

                    val destFromError = "Please input a valid address"
                    val destFromToError = "Please input a valid address"

                    fun validateData(from: String, to: String): Boolean {

                        validateDestFrom = from.isNotBlank()
                        validateDestTo = to.isNotBlank()

                        return (validateDestFrom && validateDestTo)
                    }

                    fun registerRide(
                        from: String,
                        to: String,
                    ){
                        if(validateData(from, to)) {
                            val navigate1 = Intent(this@ScheduleRide1, ScheduleRide2::class.java)
                            navigate1.putExtra("From", destinationFrom.trim())
                            navigate1.putExtra("To", destinationTo.trim())
                            startActivity(navigate1)
                            finish()
                        }
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 70.dp)
//                            .verticalScroll(rememberScrollState())
                    )
                    {
                        Column() {
                            Text(
                                text = "Schedule",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                modifier = Modifier.offset(x = 40.dp)
                            )
                            Text(
                                text = "a ride",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFEE5252),
                                modifier = Modifier.offset(x = 40.dp)
                            )
                        }

                        // COLUMN FOR USER INFO INPUT
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp, vertical = 20.dp)
//                                .verticalScroll(rememberScrollState())
//                                .weight(weight = 1f, fill = false)
                        ) {

                            Text(
                                text = "Where are we going?",
                                fontSize = 24.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )

                            Spacer(Modifier.height(20.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "From",
                                hint = "Comrat",
                                activeVariable = destinationFrom,
                                onVarChange = {
                                    destinationFrom = it
                                },
                                errorMessage = destFromError,
                                showError = !validateDestFrom
                            )
                            Spacer(Modifier.height(10.dp))
                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "To",
                                hint = "Chisinau",
                                activeVariable = destinationTo,
                                onVarChange = {
                                    destinationTo = it
                                },
                                errorMessage = destFromToError,
                                KeyboardSettings = KeyboardOptions(
                                    imeAction = ImeAction.Done
                                ),
                                showError = !validateDestTo
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    )
                    {
                        Button(
                            onClick = {
                                val navigate = Intent(this@ScheduleRide1, AvailableRides::class.java)
                                startActivity(navigate)
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(160.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color(0xFFEE5252)
                            )
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                        Button(
                            onClick = {
                                registerRide(
                                    destinationFrom,
                                    destinationTo,
                                )
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(160.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color(
                                    0xFFEE5252
                                )
                            )
                        ) {
                            Text(
                                text = "Next",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
            }
        }
    }
}