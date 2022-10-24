package com.example.app2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2_2Theme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.app2.R.drawable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import java.security.AllPermission

class Sign_in : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                // A surface container using the 'background' color from the theme
//              //Choose your role page
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp, horizontal = 40.dp)
                    ) {
                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .offset(y = 130.dp, x = 120.dp),
////                        verticalArrangement = Arrangement.Center,
////                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Welcome",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFEE5252)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
//                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painter = painterResource(id = drawable.logo_fig),
                                contentDescription = "Person",
//                            tint = Color.White,
                                modifier = Modifier.size(100.dp))
                        }

                        Column(
                            modifier = Modifier
//                                .fillMaxSize()
//                                .offset(y = 340.dp, x = 40.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Username",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            InputBar(
                                hint = "",
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(330.dp)
//                                .padding(1.dp)
                            )
                            Spacer(Modifier.height(5.dp))

                            Text(
                                text = "Password",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            InputBar(
                                hint = "",
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(330.dp)
//                                .padding(1.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End
//                                .offset(y = 480.dp, x = 230.dp)
                        )
                        {
                            Text(
                                text = "Forgot Password?",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Blue
                            )
                        }

                        Spacer(Modifier.height(20.dp))

                        Column(
                            modifier = Modifier,
//                                .fillMaxSize(),
//                                .padding(40.dp)
//                                .offset(y = 0.dp, x = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = spacedBy(10.dp, Alignment.Bottom)
                        )
                        {
                            Button(
                                onClick = {
                                    val navigate1 = Intent(this@Sign_in, SignInProcess::class.java)


                                    startActivity(navigate1)
                                    finish()
                                },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252)),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(350.dp)
                            ) {
                                Text(
                                    text = "Sign in",
                                    fontSize = 19.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFFFFFFF)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
//                                .offset(y = 620.dp, x = 200.dp),
//                        verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "or",
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )
                            }

                            Button(
                                onClick = {
                                        val navigate1 = Intent(this@Sign_in, SignInFacebook::class.java)

                                        startActivity(navigate1)
                                        finish()
                                          },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFF4267B2)),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(350.dp)
                            ) {
                                Image(painter = painterResource(id = drawable.facebook),
                                    contentDescription = "Facebook icon",
//                            tint = Color.White,
                                    modifier = Modifier.size(20.dp))

                                Text(
                                    text = "Sign in with Facebook",
                                    fontSize = 19.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFFFFFFF)
                                )
                            }
                            Button(
                                onClick = {
                                        val navigate1 = Intent(this@Sign_in, SignInGoogle::class.java)


                                        startActivity(navigate1)
                                        finish()
                                          },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(350.dp)
                            ) {
                                Image(painter = painterResource(id = drawable.google),
                                    contentDescription = "Google icon",
//                              tint = Color.White,
                                    modifier = Modifier.size(30.dp))
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(
                                    text = "Sign in with Google",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 19.sp,
                                    color = Color(0xFF000000)
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
fun InputBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf(hint)
    }
    var isHintDisplayed by remember{
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color(0xFF888686)),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color(0xFFFAF7F7), CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
//                    isHintDisplayed = it != FocusState.hasFocus
                }
        )
    }
}
