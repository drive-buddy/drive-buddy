package com.example.app2.rides.booking


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.CardRideDetails
import com.example.app2.MoreAvailableRides
import com.example.app2.Schedule_ride
import com.example.app2.drawer.DrawerLayout
import com.example.app2.helperfiles.*
import com.example.app2.rides.SharedViewModel
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

class PassengerBookRide : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    var showError : Boolean = false
                    val dbEntry : DBHelper = DBHelper(null)
                    val userEmail : String = dbEntry.getCurrentUser()

//                    val rideId : String? = intent.getStringExtra("info")
                    val rideId = intent.getStringExtra("rideId")
                    val driver = intent.getStringExtra("driver")
                    val passenger1 = intent.getStringExtra("passenger1")
                    val passenger2 = intent.getStringExtra("passenger2")
                    val passenger3 = intent.getStringExtra("passenger3")
                    val date : String? = intent.getStringExtra("date")
                    val nrOfSeats : String? = intent.getStringExtra("nrOfSeats")
//                    var spot : Int = 1
//                    while (spot <= 3) {
//                        spot += 1
//                    }
                    if (passenger1 == "") {
                        dbEntry.addPassengerToRide(rideId!!, 1, userEmail)
                    } else if (passenger2 == "") {
                        dbEntry.addPassengerToRide(rideId!!, 2, userEmail)
                    } else if (passenger3 == "") {
                        dbEntry.addPassengerToRide(rideId!!, 3, userEmail)
                    } else {
                        Log.e("Booking Ride", "No available seats")
                        showError = true
                    }
                    DrawerLayout(localContext = this,
                        contentFun = {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                                .background(Color(0xFFEE5252)),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            if (!showError) {
                                Text("Ride Successfully",
                                    color = Color.Black,
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 20.dp, 0.dp,0.dp))
                                Text("Booked!",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 0.dp))
                            }
                            CardRideDetails(showError, date!!, nrOfSeats!!)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .size(30.dp)
                                    .padding(20.dp, 0.dp),
                                elevation = 10.dp,
                                shape = RoundedCornerShape(20.dp)
                            ) { Text("More available rides:",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp)
                                ,
                            ) }
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                // Add a single item
                                item {
                                    MoreAvailableRides()
                                }

                                // Add 5 items
                                items(5) {
                                    MoreAvailableRides()
                                }

                                item {
                                    Text(text = "No more available rides")
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun CardRideDetails(showError : Boolean, date : String = "", nrOfSeats : String = "") {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
            .padding(20.dp)
            .fillMaxWidth()
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        if (showError) {
            Text(
                text = "Sorry, but all the seats are taken.\n" +
                        "Try another ride.",
                color = Color(0xFFEE5252),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
        } else {
            Text(
                text = "You have booked a ride \n" +
                        "for $date.\n" +
                        "$nrOfSeats seats have been reserved",
                color = Color(0xFFEE5252),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
        }

    }
}