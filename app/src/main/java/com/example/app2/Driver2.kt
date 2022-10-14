package com.example.app2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2_2Theme
import com.example.app2.Driver1

class Driver2 : ComponentActivity() {
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
                            .offset(y = 60.dp, x = 40.dp),
                    ) {

                        Text(
                            text = "Profile",
                            fontSize = 42.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 130.dp, x = 140.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.im2),
                            contentDescription = "Person",
//                            tint = Color.White,
                            modifier = Modifier.size(100.dp))
                    }

                    Box(
                        modifier = Modifier
                            .absolutePadding(
                                left = 140.dp,
                                top = 230.dp,
                                right = 0.dp,
                                bottom = 0.dp
                            )
                    ) {

                        Button(
                            onClick = { /* TO DO */ },
                            modifier = Modifier
                                .size(15.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
                        ) {

//                            Image(painter = painterResource(id = R.drawable.add),
//                                contentDescription = "add",
////                            tint = Color.White,
//                                modifier = Modifier.size(50.dp))
                            Icon(
                                Icons.Default.Add,
                                modifier = Modifier.size(20.dp),
                                contentDescription = "add",
                                tint = Color.White
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 230.dp, x = 165.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Add photo",
                            fontSize = 13.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 290.dp, x = 40.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Name",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "Surname",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "Gender",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "M/F",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "Birth date",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "      YYYY          |            MM          |          DD      ",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "Phone number",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "+373 ___ __ ___",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "Location",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    )
                    {
                        Button(
                            onClick = {
                                val navigate = Intent(this@Driver2, Driver1::class.java)
                                startActivity(navigate)
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(160.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
                        ) {
                            Text(
                                text = "Back",
                                fontSize = 30.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                        Button(
                            onClick = {
                                val navigate1 = Intent(this@Driver2, Driver3::class.java)
                                startActivity(navigate1)
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(160.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
                        ) {
                            Text(
                                text = "Next",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 30.sp,
                                color = Color(0xFFFFFFFF)
                            )
                        }

                    }
                }
            }
        }
    }
}





