package com.example.app2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
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
import androidx.compose.ui.res.painterResource
import com.example.app2.R.drawable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import java.security.AllPermission

class Sign_in : ComponentActivity() {

    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                // A surface container using the 'background' color from the theme
//              //Choose your role page
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
//                        .weight(weight = 1f, fill = false),,
                    color = MaterialTheme.colors.background,
                )
                {
                    var userHashMap : HashMap<String, String> = HashMap<String, String> ()
                    var userEmail by rememberSaveable { mutableStateOf("") }
                    var userPassword by rememberSaveable { mutableStateOf("") }

                    var errorMsg : String = ""
                    var showErrorMsg : Boolean = false

                    if (intent.getStringExtra("signIn_error") != null)
                    {
                        val temp : List<String> = intent
                            .getStringExtra("signIn_error")!!
                            .split(": ")
                        errorMsg = temp[1]

                        showErrorMsg = true

                    }

                    val isPasswordValid by derivedStateOf {
                        userPassword.length > 7
                    }

                    var isPasswordVisible by remember {
                        mutableStateOf(false)
                    }

                    val icon = if(isPasswordVisible){
                        painterResource(id = R.drawable.ic_baseline_visibility_24)
                    }
                    else{
                        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp, horizontal = 40.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
//                                .offset(y = 130.dp, x = 120.dp),
////                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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

                            PrettyBar(
                                type = "Email",
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),
                                activeVariable = userEmail,
                                onVarChange = {
                                    userEmail = it
                                },
                                showError = showErrorMsg,
                                errorMessage = errorMsg
                            )

                            userHashMap["email"] = userEmail.trim()

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                type = "Password",
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),
//                                .padding(1.dp)
                                activeVariable = userPassword,
                                onVarChange = {
                                    userPassword = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Next),

                                keyboardTransformation =
                                if(isPasswordVisible) VisualTransformation.None
                                else PasswordVisualTransformation(),

                                trailingI = {
                                    IconButton(onClick = {
                                        isPasswordVisible = !isPasswordVisible
                                    }) {
                                        Icon(painter = icon,
                                            contentDescription ="visibility icon"
                                        )
                                    }
                                },
                                showError = showErrorMsg
                                )

                            userHashMap["password"] = userPassword.trim()

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

                                    navigate1.putExtra("email", userHashMap["email"])
                                    navigate1.putExtra("password", userHashMap["password"])

                                    startActivity(navigate1)
//                                    finish()
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