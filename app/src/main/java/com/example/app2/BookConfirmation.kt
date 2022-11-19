package com.example.app2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2_2Theme
import com.example.app2.ui.theme.Black

import androidx.compose.foundation.layout.size


class BookConfirmation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                TopBar()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = " ")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, "menuIcon")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color(0xFFEE5252),
                elevation = 10.dp
            )
        }, content = { ConfirmationContent() })
}

@Composable
fun ConfirmationContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Book",
            fontSize = 30.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(30.dp,0.dp)
        )
        Text(
            text = "Confirmation",
            fontSize = 30.sp,
            color = Color(0xFFEE5252),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(30.dp, 0.dp)
        )
        CardRide()

        Row(
            Modifier.fillMaxSize().padding(20.dp, 1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Button(
                onClick = {},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
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
                onClick = {},
                modifier = Modifier
                    .height(50.dp)
                    .width(140.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

@Composable
fun CardRide() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .padding(20.dp, 0.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp, 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround

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

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Column {
                            Canvas(modifier = Modifier.size(10.dp), onDraw = {
                                drawCircle(color = Color(0xFFEE5252))
                            })
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
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

                Spacer(modifier = Modifier.width(100.dp))

                Column {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_round),
                            contentDescription = "Profile Picture",
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
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
//                                    brush = Brush.horizontalGradient(listOf(Gray, Black)),
//                                    shape = RoundedCornerShape(20.dp)
//                                ),
//                        ){
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center,
//                            ){
//                                Icon(imageVector = Icons.Default.Star, contentDescription = "Rank")
//                                Text(text = "Rank",
//                                    fontSize = 10.sp)
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
            Column {
                Row {
                    Column {
                        Text(
                            text = "Date",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .offset(0.dp, 10.dp)
                        )
                        Card(
                            Modifier
                                .size(width = 160.dp, height = 30.dp)
                                .offset(0.dp, 20.dp),
                            backgroundColor = Color.LightGray) {
                            // Card content
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Card(
                        Modifier
                            .size(width = 180.dp, height = 30.dp)
                            .offset(0.dp, 42.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Text(
                        text = "Nr of seats",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 160.dp, height = 30.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Text(
                        text = "Card/Cash",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 360.dp, height = 30.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Text(
                        text = "Notes",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 360.dp, height = 55.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.smoking_round), // smoking_no_round | smoking_round
                    contentDescription = "Smoking / No Smoking",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White)
                        .clip(CircleShape) // clip to the circle shape
                        .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                )

                Image(
                    painter = painterResource(R.drawable.pet_no_round), // pet_no_round | pet_round
                    contentDescription = "Pet | No Pet",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White)
                        .clip(CircleShape) // clip to the circle shape
                        .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                )

                Image(
                    painter = painterResource(R.drawable.luggage_round), // luggage_round | luggage_no_round
                    contentDescription = "Luggage | No Luggage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White)
                        .clip(CircleShape) // clip to the circle shape
                        .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    App2_2Theme {
        TopBar()
    }
}