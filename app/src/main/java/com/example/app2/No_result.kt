package com.example.app2
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2Theme
import com.example.app2.ui.theme.bgRed
import com.example.app2.ui.theme.Grey
class No_result : ComponentActivity() {
    var db: DBHelper = DBHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            App2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = bgRed
                ) {

                    Box(modifier = Modifier
                        .absolutePadding(
                            left = 5.dp)
                    ) {

                        Button(onClick = { /* */ },
                            modifier = Modifier
                                .size(60.dp),
                            shape = CircleShape

                        ) {

                            Icon(
                                Icons.Default.Menu,
                                modifier = Modifier.fillMaxSize(),
                                contentDescription = "menu",
                                tint = Color.Black)
                        }
                    }

                    Box(modifier = Modifier
                        .absolutePadding(
                            left = 320.dp,
                            top = 10.dp)
                    ) {

                        Button(onClick = { /**/ },
                            modifier = Modifier
                                .size(60.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)

                        ) {

                            Icon(
                                Icons.Default.Search,
                                modifier = Modifier.fillMaxSize(),
                                contentDescription = "search menu",
                                tint = bgRed)
                        }
                    }

                    Box(modifier = Modifier
                        .absolutePadding(
                            left = 35.dp,
                            top = 65.dp)
                    ) {

                        Card(modifier = Modifier
                            .height(30.dp)
                            .width(170.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = 5.dp,
                        ) {
                            Text(
                                text = "Results:",
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color.Black
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 40.dp, vertical = 100.dp),
                        shape = RoundedCornerShape(30.dp),
                        elevation = 5.dp
                    ) {

                        Column(modifier = Modifier
                            .padding(horizontal = 40.dp, vertical = 145.dp)
                        ) {

                            Text(
                                text = "There are no rides set yet",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                color = Grey
                            )
                            Image(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )

                        }
                    }
                }

                Box(modifier = Modifier
                    .absolutePadding(
                        left = 310.dp,
                        top = 680.dp,
                        right = 0.dp,
                        bottom = 0.dp)
                ) {

                    Button(onClick = {
                        val navigate = Intent(this@No_result, Schedule_ride::class.java)
                        startActivity(navigate)
                                     },
                        modifier = Modifier.size(70.dp),
                        shape = CircleShape,
                        border = BorderStroke(2.dp, Color.White),
                    ) {

                        Icon(Icons.Default.Add,
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = "add",
                            tint = Color.White)
                    }
                }

                Box(modifier = Modifier
                    .absolutePadding(
                        left = 110.dp,
                        top = 730.dp,
                        right = 0.dp,
                        bottom = 0.dp)
                ) {

                    Button(onClick = { /**/ },
                        modifier = Modifier
                            .height(40.dp)
                            .width(170.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)

                    ) {

                        Text(text = "Back",
                            color = bgRed,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

