package com.example.app2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.signin.SignIn
import com.example.app2.signup.Choose
import com.example.app2.ui.theme.App2Theme

class StartPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                App2Theme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = 140.dp, x = 55.dp),
                        ) {
                            Text(
                                text = "Find your",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Text(
                                text = "Drive Buddy",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Bottom)
                        )
                        {
                            Button(
                                onClick = {
                                    val navigate = Intent(this@StartPage, Choose::class.java)
                                    startActivity(navigate)
                                },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                            ) {
                                Text(
                                    text = "Create Account",
                                    fontSize = 30.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFEE5252)
                                )
                            }
                            Button(
                                onClick = {
                                    val navigate1 = Intent(this@StartPage, SignIn::class.java)
                                    startActivity(navigate1)
                                },
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(
                                    text = "Sign In",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
