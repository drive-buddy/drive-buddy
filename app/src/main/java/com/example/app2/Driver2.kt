package com.example.app2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userHashMap : HashMap<String, String?> = HashMap<String, String?> ()

                    userHashMap["email"] = intent.getStringExtra("email")
                    userHashMap["userName"] = intent.getStringExtra("username")
                    userHashMap["userPassword"] = intent.getStringExtra("password")
                    userHashMap["type"] = intent.getStringExtra("type")

                    var name by rememberSaveable {
                        mutableStateOf("")
                    }

                    var surname by rememberSaveable {
                        mutableStateOf("")
                    }

                    var gender by rememberSaveable {
                        mutableStateOf("")
                    }

                    var birthDate by rememberSaveable {
                        mutableStateOf("")
                    }

                    var phoneNr by rememberSaveable {
                        mutableStateOf("")
                    }

                    var carModel by rememberSaveable {
                        mutableStateOf("")
                    }

                    var carPlate by rememberSaveable {
                        mutableStateOf("")
                    }

                    var yearOfExp by rememberSaveable {
                        mutableStateOf("")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp),
                    )
                    {
                        Column() {
                            Text(
                                text = "Profile",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                modifier = Modifier.offset(x = 40.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 30.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.im2),
                                contentDescription = "Person",
//                                tint = Color.White,
                                modifier = Modifier
                                    .size(100.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
//                                    .offset(y = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center

                        )
                        {
                            Button(
                                onClick = { /* TO DO */ },
                                modifier = Modifier
                                    .size(16.dp),
                                shape = CircleShape,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    backgroundColor = Color(
                                        0xFFEE5252
                                    )
                                )
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    modifier = Modifier.size(200.dp),
                                    contentDescription = "add",
                                    tint = Color.White
                                )

                            }
                            Text(
                                text = "Add photo",
                                fontSize = 13.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF888686),
                                modifier = Modifier
                                    .offset(x = 10.dp)
                            )


                        }
                        Spacer(Modifier.height(10.dp))
                        // COLUMN FOR USER INFO INPUT
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 40.dp, vertical = 20.dp)
                                .verticalScroll(rememberScrollState())
                                .weight(weight = 1f, fill = false)
                        ) {

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Name",
                                activeVariable = name,
                                onVarChange = {
                                    name = it
                                }
                            )

                            userHashMap["userFirstName"] = name

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Surname",
                                activeVariable = surname,
                                onVarChange = {
                                    surname = it
                                }
                            )

                            userHashMap["userSurname"] = surname

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Gender",
                                hint = "M/F",
                                activeVariable = gender,
                                onVarChange = {
                                    gender = it
                                }
                            )

                            userHashMap["userGender"] = gender

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Birth date",
                                hint = "YYYY | MM | DD",
                                activeVariable = birthDate,
                                onVarChange = {
                                    birthDate = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                )
                            )

                            userHashMap["userBirthDate"] = birthDate

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Phone number",
                                hint = "+373 ___ __ ___",
                                activeVariable = phoneNr,
                                onVarChange = {
                                    phoneNr = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone,
                                    imeAction = ImeAction.Next
                                )
                            )

                            userHashMap["userPhoneNumber"] = phoneNr

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Car Model",
                                hint = "Dacia Logan",
                                activeVariable = carModel,
                                onVarChange = {
                                    carModel = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )

                            userHashMap["carModel"] = carModel

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Car Licence Plate",
                                hint = "ABC 123",
                                activeVariable = carPlate,
                                onVarChange = {
                                    carPlate = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Years of experience",
                                hint = "5",
                                activeVariable = yearOfExp,
                                onVarChange = {
                                    yearOfExp = it
                                },
                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )

                            userHashMap["yearOfExp"] = yearOfExp
                            Spacer(Modifier.height(15.dp))
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 20.dp),
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
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color(0xFFEE5252)
                            )
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
                                val dbEntry: DBHelper = DBHelper()

                                dbEntry.addUser(userHashMap)

                                val navigate1 = Intent(this@Driver2, SignUpProcess::class.java)

                                navigate1.putExtra("email", userHashMap["email"])
                                navigate1.putExtra("password", userHashMap["userPassword"])
                                navigate1.putExtra("type", userHashMap["type"])

                                startActivity(navigate1)
                                finish()
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(160.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color(
                                    0xFFEE5252
                                )
                            )
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

