package com.example.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.bgRed
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.app2.ui.theme.App2_2Theme

class Schedule_ride: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userHashMap: HashMap<String, String?> = HashMap<String, String?>()

                    var from by rememberSaveable {
                        mutableStateOf("")
                    }

                    var to by rememberSaveable {
                        mutableStateOf("")
                    }
                    var nrOfSeats by rememberSaveable {
                        mutableStateOf("")
                    }

                    var price by rememberSaveable {
                        mutableStateOf("")
                    }

//                    var gender by rememberSaveable {
//                        mutableStateOf("")
//                    }
//
//                    var birthDate by rememberSaveable {
//                        mutableStateOf("")
//                    }
//
//                    var phoneNr by rememberSaveable {
//                        mutableStateOf("")
//                    }
//
//                    var carModel by rememberSaveable {
//                        mutableStateOf("")
//                    }
//
//                    var carPlate by rememberSaveable {
//                        mutableStateOf("")
//                    }
//
//                    var yearOfExp by rememberSaveable {
//                        mutableStateOf("")
//                    }
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
                                activeVariable = from,
                                onVarChange = {
                                    from = it
                                }
                            )

                            userHashMap["from"] = from

//                            Spacer(Modifier.height(10.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "To",
                                activeVariable = to,
                                onVarChange = {
                                    to = it
                                }
                            )

                            userHashMap["to"] = to

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
//                                    .padding(horizontal = 30.dp, vertical = 20.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                ShowDatePicker()
                                Spacer(Modifier.width(10.dp))
                                ShowTimePicker()

                            }
//                            ContentView(list = list)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
//                                    .padding(horizontal = 30.dp, vertical = 20.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Button(
                                    onClick = {/**/ },
                                    shape = RoundedCornerShape(5.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = Color(
                                            0xFFEE5252
                                        )
                                    ),
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(40.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Person,
                                        modifier = Modifier.fillMaxSize(),
                                        contentDescription = "Person",
                                        tint = Color.White
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                PrettyBar(
                                    modifier = Modifier
                                        .height(90.dp)
                                        .width(100.dp),

                                    type = "Nr of seats",
                                    activeVariable = nrOfSeats,
                                    onVarChange = {
                                        from = it
                                    },
                                    KeyboardSettings = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Next
                                    )
                                )

                                userHashMap["nrOfSeats"] = nrOfSeats

                                Spacer(Modifier.width(10.dp))

                                PrettyBar(
                                    modifier = Modifier
                                        .height(90.dp)
                                        .width(150.dp),

                                    type = "Price",
                                    activeVariable = price,
                                    onVarChange = {
                                        price = it
                                    },
                                    KeyboardSettings = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Next
                                    )
                                )
                                userHashMap["Price"] = price

                            }
                            Spacer(Modifier.height(10.dp))
                            FilterList(filterss)
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
                            onClick = {/**/ },
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
                                val dbEntry: DBHelper = DBHelper()

                                dbEntry.addUser(userHashMap)

//                                val navigate1 = Intent(this@Driver2, Driver3::class.java)

//                                navigate1.putExtra("email", userHashMap["email"])
//                                navigate1.putExtra("password", userHashMap["userPassword"])
//                                navigate1.putExtra("type", userHashMap["type"])
//
//                                startActivity(navigate1)
                                finish()
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