package com.example.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2_2Theme
import com.example.app2.ui.theme.Black

class CurrentOrder : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                CurrentOrderTopBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentOrderTopBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(
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
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go Back",
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
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color(0xFFEE5252)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Current",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 0.dp,0.dp))
                Text("Order",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp)
                )

                CardOrder()

                Spacer(modifier = Modifier.height(20.dp))

                androidx.compose.material.Button(
                    onClick = {},
                    modifier = Modifier
                        .height(50.dp)
                        .width(140.dp)
                        .padding(0.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFEE5252)
                    )
                }
            }
        }
    )
}

@Composable
fun CardOrder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.88f)
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(15.dp)
                                .background(
                                    brush = Brush.horizontalGradient(listOf(Color.Gray, Black)),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ){
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rank"
                                )
                                Text(text = "Rank",
                                    fontSize = 10.sp)
                            }

                        }
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
                Column {
                    Text(
                        text = "Car model",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 360.dp, height = 35.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Text(
                        text = "Number",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 360.dp, height = 35.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Text(
                        text = "Years of Experience",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 360.dp, height = 35.dp)
                            .offset(0.dp, 20.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        // Card content
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Text(
                        text = "Nr. of rides",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(0.dp, 15.dp)
                    )
                    Card(
                        Modifier
                            .size(width = 160.dp, height = 35.dp)
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
                    painter = painterResource(R.drawable.smoking_no_round), // smoking_no_round | smoking_round
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
fun DefaultPreview3() {
    App2_2Theme {
        CurrentOrderTopBar()
    }
}