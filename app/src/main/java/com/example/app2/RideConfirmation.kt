@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app2.ui.theme.App2_2Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text as Text1

import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material.Card

class RideConfirmation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                AppTopBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text1(
                        " "
                    )
                },
                Modifier.background(Color.Gray),
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color(0xFFEE5252),
                            modifier = Modifier
                                .size(30.dp)
                                .background(color = Color.White,shape = CircleShape)
                                .border(1.dp, Color.White, shape = CircleShape),
                        )
                    }
                },
                backgroundColor = Color(0xFFEE5252)
            )

        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "fab icon",
                tint = Color.White,
                modifier = Modifier
                    .size(size = 60.dp)
                    .background(color = Color(0xFFEE5252)))
        } },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color(0xFFEE5252)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
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
                CardRideDetails()
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
        }
    )
}
@Composable
fun CardRideDetails() {
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
        Text(
            text = "You have booked a ride \n" +
                    "for [Date] at [time].\n" +
                    "[nr] seats have been reserved",
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

@Composable
fun MoreAvailableRides() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(280.dp)
            .padding(20.dp, 20.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row {
                Column {
                    Text(
                        "Price",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,

                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                    Text(
                        "Date/time",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row {
                        Column {
                            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                                drawCircle(color = Color(0xFFEE5252))
                            })
                            Spacer(modifier = Modifier.height(15.dp))
                            androidx.compose.material.Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxHeight(0.025f)
                                    .width(2.dp)
                                    .offset(4.dp, 0.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                                drawCircle(color = Color(0xFFEE5252))
                            })
                        }

                        Column {
                            Text(
                                text = "Point A",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .offset(10.dp, 0.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Point B",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .offset(10.dp, 0.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(95.dp))

                Column {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_round),
                            contentDescription = "Profile Picture",
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    0f
                                )
                            }),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                        )
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth(0.5f)
//                                .height(15.dp)
//                                .background(
//                                    brush = Brush.horizontalGradient(listOf(Color.Gray, Black)),
//                                    shape = RoundedCornerShape(20.dp)
//                                ),
//                        ) {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center,
//                            ) {
//                                androidx.compose.material.Icon(
//                                    imageVector = Icons.Default.Star,
//                                    contentDescription = "Rank"
//                                )
//                                Text(
//                                    text = "Rank",
//                                    fontSize = 10.sp
//                                )
//                            }
//
//                        }
                        Text(
                            text = "Name Surname",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Car model | Nr",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            androidx.compose.material.Button(
                onClick = {},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
            ) {
                Text(
                    text = "Book",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App2_2Theme {
        AppTopBar()
    }
}