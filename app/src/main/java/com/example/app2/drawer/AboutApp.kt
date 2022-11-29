package com.example.app2.drawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ConfirmationContent
import com.example.app2.MoreAvailableRides
import com.example.app2.R
import com.example.app2.ui.theme.App2_2Theme

class AboutApp : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "About Us",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFEE5252),)

//                        Greeting("${user1.userFirstName} !")
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(0.dp, 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            // Add a single item
                            item {
                                AppMission()
                            }
                            item { 
                                Text(
                                    text = "Purpose",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEE5252)
                                )
                                Text(
                                    "Our objective is to make traveling easier, " +
                                            "help students alike, those who commute to work, " +
                                            "everyone who has to reach a destination" +
                                            " and is willing to share it with others, " +
                                            "save money, make profit" +
                                            ", to encourage carpooling not only for personal, " +
                                            "instant gain but also cause it's better for the environment.",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    modifier = Modifier.padding(bottom = 5.dp)

                                )
                            }
                            item {
                                Text(
                                    text = "Mentors",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEE5252),
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )

                                Row(
                                    Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceEvenly ,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(R.drawable.ic_launcher_round),
                                            contentDescription = "Profile Picture",
                                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(CircleShape)
                                        )
                                        Text(
                                            text = "Alexandru CEBOTARI",
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black)
                                    }
                                    Column {
                                        Image(
                                            painter = painterResource(R.drawable.ic_launcher_round),
                                            contentDescription = "Profile Picture",
                                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(CircleShape)
                                        )
                                        Text(
                                            text = "Iulia PALII",
                                            fontSize = 10.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black)
                                    }
                                }


                            }

                            item {
                                Text(
                                    text = "Our Team",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEE5252),
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "PROGRAMMING-TEAM",
                                        fontSize = 13.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.padding(bottom = 5.dp)
                                    )
                                    Row(
                                        Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceAround ,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Image(
                                                painter = painterResource(R.drawable.ic_launcher_round),
                                                contentDescription = "Profile Picture",
                                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(80.dp)
                                                    .clip(CircleShape)
                                            )
                                            Text(
                                                text = "Maria PROCOPII",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black)
                                        }

                                        Column {
                                            Image(
                                                painter = painterResource(R.drawable.ic_launcher_round),
                                                contentDescription = "Profile Picture",
                                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(80.dp)
                                                    .clip(CircleShape)
                                            )
                                            Text(
                                                text = "Dumitru MORARU\n" +
                                                        "Team Leader",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black)
                                        }

                                        Column {
                                            Image(
                                                painter = painterResource(R.drawable.ic_launcher_round),
                                                contentDescription = "Profile Picture",
                                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(80.dp)
                                                    .clip(CircleShape)
                                            )
                                            Text(
                                                text = "Denis SMOCVIN",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black)
                                        }

                                    }

                                    Text(
                                        text = "DESIGNER-TEAM",
                                        fontSize = 13.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.padding(bottom = 5.dp)
                                    )
                                    Row(
                                        Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceEvenly ,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Image(
                                                painter = painterResource(R.drawable.ic_launcher_round),
                                                contentDescription = "Profile Picture",
                                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(80.dp)
                                                    .clip(CircleShape)
                                            )
                                            Text(
                                                text = "Irina RACOVCENA",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black)
                                        }
                                        Column {
                                            Image(
                                                painter = painterResource(R.drawable.ic_launcher_round),
                                                contentDescription = "Profile Picture",
                                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(80.dp)
                                                    .clip(CircleShape)
                                            )
                                            Text(
                                                text = "Maia ZAICA",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black)
                                        }
                                    }
                                }
                            }

                            item {
                                Contacts()
                            }

                            item {
                                Text(
                                    text = "Thank You!",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEE5252)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Contacts() {
    Row(
        Modifier.fillMaxSize()
            .padding(top = 10.dp,bottom = 10.dp)
    ) {
        Text(
            text = "Contacts: ",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Column {
            Text(
                text = "dmoraru@pentalog.com" +
                        "\nmprocopii@pentalog.com" +
                        "\ndsmocin@pentalog.com" +
                        "\niracovcena@pentalog.com" +
                        "\nmzaica@pentalog.com",
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

        }
        
    }

}

@Composable
fun AppMission() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .padding(0.dp, 10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = "To make traveling less stressful, more comfortable, more convenient, " +
                            " on a bigger scale increase employment rate, reduce fuel consumption.",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Drive Buddy MISSION STATEMENT",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFEE5252),
                )
            }
        }
    }
}