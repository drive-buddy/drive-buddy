package com.example.app2.rides.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.helperfiles.DBHelper
import com.example.app2.helperfiles.FilterList
import com.example.app2.helperfiles.allFilters
import com.example.app2.rides.AvailableRides
import com.example.app2.ui.theme.App2_2Theme
import com.google.firebase.Timestamp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.collections.HashMap

class ScheduleRide3: ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var filterHashMap = HashMap<String, String?>()

                    fun registerRide() {
                        val dbEntry : DBHelper = DBHelper(null)
                        val userEmail : String = dbEntry.getCurrentUser()

                        var result : HashMap<String, Any?> = HashMap<String, Any?>()

                        result["from"] = intent.getStringExtra("From")
                        result["to"] = intent.getStringExtra("To")
                        result["nrOfSeats"] = intent.getStringExtra("NrOfSeats")
                        result["price"] = intent.getStringExtra("Price")
                        val tmpTime = intent.getLongExtra("Time", 0L)
                        result["date"] = Timestamp(tmpTime, 0)

                        result = (result + filterHashMap) as HashMap<String, Any?>

                        dbEntry.getUser(userEmail) {
                            document ->
                            if (document["type"] == "passenger")
                            {
                                result["driver"] = ""
                                result["passenger1"] = document["email"] as String?
                                result["passenger2"] = ""
                                result["passenger3"] = ""
                                dbEntry.addPassengerRequest(result) {
                                    dbEntry.setCurrentOrder(userEmail, it)
                                }
                            }
                            else if (document["type"] == "driver")
                            {
                                result["driver"] = document["email"] as String?
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
                                Log.i("info2", document.toString())
                            }

                            Toast.makeText(applicationContext, "Ride successfully scheduled.", Toast.LENGTH_SHORT).show()
                        }

                        val navigate1 = Intent(this@ScheduleRide3, AvailableRides::class.java)
                        startActivity(navigate1)
                        finish()
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
                                text = "Do you have any requests?",
                                fontSize = 24.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                            Spacer(Modifier.height(15.dp))
                            filterHashMap = FilterList(allFilters)
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
                                val navigate = Intent(this@ScheduleRide3, AvailableRides::class.java)
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
                                registerRide()
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