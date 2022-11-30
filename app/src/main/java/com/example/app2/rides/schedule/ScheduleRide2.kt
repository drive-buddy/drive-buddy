package com.example.app2.rides.schedule

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import com.example.app2.helperfiles.PrettyBar
import com.example.app2.ShowDatePicker
import com.example.app2.ShowTimePicker
import com.example.app2.helperfiles.DBHelper
import com.example.app2.rides.AvailableRides
import com.example.app2.ui.theme.App2_2Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import kotlin.collections.HashMap

class ScheduleRide2: ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val from : String? = intent.getStringExtra("From")
                    val to : String? = intent.getStringExtra("To")

                    var date: Long by rememberSaveable { mutableStateOf(0) }
                    var time: Long by rememberSaveable { mutableStateOf(0) }
                    var nrOfSeats: String by rememberSaveable { mutableStateOf("") }
                    var price: String by rememberSaveable { mutableStateOf("") }

                    var validateDate by rememberSaveable { mutableStateOf(true) }
                    val validateTime by rememberSaveable { mutableStateOf(true) }
                    var validateNrOfSeats by rememberSaveable { mutableStateOf(true) }
                    var validatePrice by rememberSaveable { mutableStateOf(true) }

                    val dateError = "Please input a valid date"
                    val timeError = "Please input a valid time"
                    val nrOfSeatsError = "Please input a valid number of seats"
                    val priceError = "Please input a valid price"

                    fun validateData(time: Long,
                         nrOfSeats: String?, price: String?): Boolean{

                        validateNrOfSeats = !nrOfSeats.isNullOrBlank() && (nrOfSeats.toInt() <= 3)
                        validatePrice = !price.isNullOrBlank()

                        validateDate = time >= Calendar.getInstance().timeInMillis / 1000
//                        val dateRegex = ("(0[1-9]|1\\d|2\\d|3[01])\\/" +
//                                "(0[1-9]|1[0-2])\\/(19|20)\\d{2}").toRegex()
//
//                        val timeRegex = ("([01][0-9]|2[0-3]):([0-5][0-9])").toRegex()

//                        validateDate = dateRegex.matches(date)
//                        validateTime = timeRegex.matches(time)

                        return (validateDate && validateTime
                                && validateNrOfSeats && validatePrice)
                    }

                    fun registerRide(
                        date: Long,
                        time: Long,
                        nrOfSeats: String?,
                        price: String?,
                    ){
                        if(validateData(date + time, nrOfSeats, price)){
                            val dbEntry : DBHelper = DBHelper(null)
                            val userEmail : String = dbEntry.getCurrentUser()
                            var userInfo : Map<String, Any?> = HashMap<String, Any>()

                            val navigate1 = Intent(this@ScheduleRide2, ScheduleRide3::class.java)
                            navigate1.putExtra("Time", (date + time))
                            navigate1.putExtra("NrOfSeats", nrOfSeats)
                            navigate1.putExtra("Price", price)
                            navigate1.putExtra("From", from)
                            navigate1.putExtra("To", to)
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
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top
                        ) {
                            date = ShowDatePicker(
                                messageError = dateError,
                                errorState = validateDate
                            )

                            Spacer(Modifier.height(10.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                time = ShowTimePicker(
                                    messageError = timeError,
                                    errorState = validateDate
                                )
                            }

                            Spacer(Modifier.height(10.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp),
                                type = "🧑 Number of seats",
                                hint = "2",
                                activeVariable = nrOfSeats,
                                onVarChange = {
                                    nrOfSeats = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone,
                                    imeAction = ImeAction.Next
                                ),
                                errorMessage = nrOfSeatsError,
                                showError = !validateNrOfSeats
                            )

                            Spacer(Modifier.height(10.dp))
                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp),
//                                        .width(150.dp),

                                type = "Price you are willing to pay",
                                hint = "20 lei",
                                activeVariable = price,
                                onVarChange = {
                                    price = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone,
                                    imeAction = ImeAction.Done
                                ),
                                errorMessage = priceError,
                                showError = !validatePrice
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
                                val navigate = Intent(this@ScheduleRide2, AvailableRides::class.java)
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
                                    date,
                                    time,
                                    nrOfSeats,
                                    price
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