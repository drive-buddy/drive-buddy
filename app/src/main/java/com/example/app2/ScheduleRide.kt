package com.example.app2

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.text.input.KeyboardType
import com.example.app2.helperfiles.DBHelper
import com.example.app2.helperfiles.FilterList
import com.example.app2.helperfiles.filterss
import com.example.app2.rides.AvailableRides
import com.example.app2.ui.theme.App2_2Theme
import com.google.firebase.Timestamp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.collections.HashMap

class ScheduleRide: ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userHashMap: HashMap<String, Any?> = HashMap<String, Any?>()
                    var filterHashMap: HashMap<String, String?> = HashMap<String, String?>()
                    var result: HashMap<String, Any?> = HashMap<String, Any?>()

                    var destinationFrom by rememberSaveable { mutableStateOf("") }
                    var destinationTo by rememberSaveable { mutableStateOf("") }
                    var date: Long? by rememberSaveable { mutableStateOf(null) }
                    var time: Long? by rememberSaveable { mutableStateOf(null) }
                    var nrOfSeats by rememberSaveable { mutableStateOf("") }
                    var price by rememberSaveable { mutableStateOf("") }

                    var validateDestFrom by rememberSaveable { mutableStateOf(true) }
                    var validateDestTo by rememberSaveable { mutableStateOf(true) }
                    val validateDate by rememberSaveable { mutableStateOf(true) }
                    val validateTime by rememberSaveable { mutableStateOf(true) }
                    var validateNrOfSeats by rememberSaveable { mutableStateOf(true) }
                    var validatePrice by rememberSaveable { mutableStateOf(true) }

                    val destFromError = "Please input a valid address"
                    val destFromToError = "Please input a valid address"
                    val dateError = "Please input a valid date"
                    val timeError = "Please input a valid time"
                    val nrOfSeatsError = "Please input a valid nr of seats"
                    val priceError = "Please input a valid price"

                    fun validateData(
                        from: String,
                        to: String,
//                        date: String,
//                        time: String,
                        nrOfSeats: String,
                        price: String
                    ): Boolean{

//                        val dateRegex = ("(0[1-9]|1\\d|2\\d|3[01])\\/" +
//                                "(0[1-9]|1[0-2])\\/(19|20)\\d{2}").toRegex()
//
//                        val timeRegex = ("([01][0-9]|2[0-3]):([0-5][0-9])").toRegex()

                        validateDestFrom = from.isNotBlank()
                        validateDestTo = to.isNotBlank()
//                        validateDate = dateRegex.matches(date)
//                        validateTime = timeRegex.matches(time)
                        validateNrOfSeats = nrOfSeats.isNotBlank()
                        validatePrice = price.isNotBlank()

                        return validateDestFrom && validateDestTo
                                && validateNrOfSeats && validatePrice
                    }

                    fun registerRide(
                        from: String,
                        to: String,
                        date: Long?,
                        time: Long?,
                        nrOfSeats: String,
                        price: String,
                    ){
                        if(validateData(from, to, nrOfSeats, price)){
                            val dbEntry : DBHelper = DBHelper(null)
                            val userEmail : String = dbEntry.getCurrentUser()
                            var userInfo : Map<String, Any?> = HashMap<String, Any>()
//                            var dateTime : Date = Date(date)


                            userInfo.forEach { (key, value) -> Log.i("info","$key = $value") }
                            result = (userHashMap + filterHashMap) as HashMap<String, Any?>

//                            date.
                            result["date"] = Timestamp(date!! + time!!, 0)
                            result.remove("time")

                            val tmp : Int = (result["nrOfSeats"] as String).toInt()
                            result.remove("nrOfSeats")
                            result["nrOfSeats"] = tmp
                            val tmp2 : Int = (result["price"] as String).toInt()
                            result.remove("price")
                            result["price"] = tmp2

                            dbEntry.getUser(userEmail) {
                                document ->
                                userInfo = document

                                if (userInfo["type"] == "passenger")
                                {
                                    result["driver"] = ""
                                    result["passenger1"] = userInfo["email"] as String?
                                    result["passenger2"] = ""
                                    result["passenger3"] = ""
                                    dbEntry.addPassengerRequest(result) {
                                        dbEntry.setCurrentOrder(userEmail, it)
                                    }
                                }
                                else if (userInfo["type"] == "driver")
                                {
                                    result["driver"] = userInfo["email"] as String?
                                    result["passenger1"] = ""
                                    result["passenger2"] = ""
                                    result["passenger3"] = ""
                                    dbEntry.addDriverOffer(result) {
                                        dbEntry.setCurrentOrder(userEmail, it)
                                    }
                                }
                                else
                                {
                                    Log.e("error", "type not defined")
                                    Log.i("info2", userInfo.toString())
                                }
                            }

                            val navigate1 = Intent(this@ScheduleRide, AvailableRides::class.java)
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
                            userHashMap["from"] = destinationFrom.trim()

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
                                showError = !validateDestTo
                            )

                            userHashMap["to"] = destinationTo.trim()

                            Spacer(Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                date = ShowDatePicker(
                                    messageError = "",
                                    errorState = validateDate)

                                Spacer(Modifier.width(5.dp))

                                time = ShowTimePicker(
                                    messageError = "",
                                    errorState = validateTime
                                )
                            }
                            userHashMap["date"] = date.toString()
                            userHashMap["time"] = time.toString()

                            Spacer(Modifier.height(10.dp))

                            Row(
                                modifier = Modifier,
//                                    .fillMaxWidth(),
//                                    .padding(horizontal = 30.dp, vertical = 20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
//                                Button(
//                                    onClick = {/**/ },
//                                    shape = RoundedCornerShape(5.dp),
//                                    contentPadding = PaddingValues(0.dp),
//                                    colors = ButtonDefaults.outlinedButtonColors(
//                                        backgroundColor = Color(
//                                            0xFFEE5252
//                                        )
//                                    ),
//                                    modifier = Modifier
//                                        .height(40.dp)
//                                        .width(40.dp)
//                                ) {
//                                    Icon(
//                                        Icons.Default.Person,
//                                        modifier = Modifier.fillMaxSize(),
//                                        contentDescription = "Person",
//                                        tint = Color.White
//                                    )
//                                }
//                                Spacer(Modifier.width(3.dp))
                                PrettyBar(
                                    modifier = Modifier
                                        .height(90.dp)
                                        .width(160.dp),
                                    type = "🧑 Nr of seats",
                                    hint = "2",
                                    activeVariable = nrOfSeats,
                                    onVarChange = {
                                        nrOfSeats = it
                                    },
                                    KeyboardSettings = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Next
                                    ),
                                    showError = !validateNrOfSeats
                                )

                                userHashMap["nrOfSeats"] = nrOfSeats

                                Spacer(Modifier.width(10.dp))

                                PrettyBar(
                                    modifier = Modifier
                                        .height(90.dp)
                                        .width(160.dp),
//                                        .width(150.dp),

                                    type = "Price",
                                    hint = "20 lei",
                                    activeVariable = price,
                                    onVarChange = {
                                        price = it
                                    },
                                    KeyboardSettings = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Done
                                    ),
                                    showError = !validatePrice
                                )
                                userHashMap["price"] = price

                            }
                            Spacer(Modifier.height(10.dp))
                            filterHashMap = FilterList(filterss)
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
                                val navigate = Intent(this@ScheduleRide, AvailableRides::class.java)
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
                                text = "Confirm",
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