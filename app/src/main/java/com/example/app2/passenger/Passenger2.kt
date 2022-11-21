package com.example.app2.passenger

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.helperfiles.DBHelper
import com.example.app2.PrettyBar
import com.example.app2.R
import com.example.app2.helperfiles.Content
import com.example.app2.signup.SignUpProcess
import com.example.app2.ui.theme.App2_2Theme

class Passenger2 : ComponentActivity() {
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

                    var name by rememberSaveable { mutableStateOf("") }
                    var surname by rememberSaveable { mutableStateOf("") }
                    var gender by rememberSaveable { mutableStateOf("") }
                    var birthDate by rememberSaveable { mutableStateOf("") }
                    var phoneNr by rememberSaveable { mutableStateOf("") }

                    var validateName by rememberSaveable { mutableStateOf(true) }
                    var validateSurname by rememberSaveable { mutableStateOf(true) }
                    var validateGender by rememberSaveable { mutableStateOf(true) }
                    var validateBirthDate by rememberSaveable { mutableStateOf(true) }
                    var validatePhoneNr by rememberSaveable { mutableStateOf(true) }

                    val validateNameError = "Please input a valid name"
                    val validateSurnameError = "Please input a valid Surname"
                    val validateGenderError = "Please input a valid Gender: M/F"
                    val validateBirthDateError = "Please input birth date in required form"
                    val validatePhoneNrError = "Please input a valid phone number"

                    fun validateData(name: String,
                                     surname: String,
                                     gender: String,
                                     birthDate: String,
                                     phoneNr: String): Boolean{

                        val genderRegex = "(?=.*[MF]).{1,}".toRegex()

                        val birthDateRegex = ("(0[1-9]|1\\d|2\\d|3[01])\\/" +
                                "(0[1-9]|1[0-2])\\/(19|20)\\d{2}").toRegex()


                        validateName = name.isNotBlank()
                        validateSurname = surname.isNotBlank()
                        validateGender = genderRegex.matches(gender)
                        validateBirthDate = birthDateRegex.matches(birthDate)
                        validatePhoneNr = Patterns.PHONE.matcher(phoneNr).matches()

                        return validateName && validateSurname && validateGender
                                && validateBirthDate && validatePhoneNr
                    }

                    fun register(
                        name: String,
                        surname: String,
                        gender: String,
                        birthDate: String,
                        phoneNr: String
                    ){
                        if(validateData(name, surname, gender, birthDate, phoneNr)){
                            val dbEntry : DBHelper = DBHelper(null)

                            dbEntry.addUser(userHashMap)

                            val navigate1 = Intent(this@Passenger2, SignUpProcess::class.java)

                            navigate1.putExtra("email", userHashMap["email"])
                            navigate1.putExtra("password", userHashMap["userPassword"])
                            navigate1.putExtra("type", userHashMap["type"])

                            startActivity(navigate1)
                            finish()
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
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

                        Spacer(Modifier.height(15.dp))
                        Content()
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
                                },
                                errorMessage = validateNameError,
                                showError = !validateName
                            )

                            userHashMap["userFirstName"] = name.trim()

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Surname",
                                activeVariable = surname,
                                onVarChange = {
                                    surname = it
                                },
                                errorMessage = validateSurnameError,
                                showError = !validateSurname
                            )

                            userHashMap["userSurname"] = surname.trim()

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
                                },
                                errorMessage = validateGenderError,
                                showError = !validateGender
                            )

                            userHashMap["userGender"] = gender

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Birth date",
                                hint = "DD/MM/YYYY",
                                activeVariable = birthDate,
                                onVarChange = {
                                    birthDate = it
                                },
                                errorMessage = validateBirthDateError,
                                showError = !validateBirthDate
                            )

                            userHashMap["userBirthDate"] = birthDate.trim()

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
                                    imeAction = ImeAction.Done),
                                errorMessage = validatePhoneNrError,
                                showError = !validatePhoneNr
                            )

                            userHashMap["userPhoneNumber"] = phoneNr.trim()

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
                                val navigate = Intent(this@Passenger2, Passenger1::class.java)
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
                                fontSize = 25.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                        Button(
                            onClick = {
                                      register(name, surname, gender, birthDate, phoneNr)
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
                                fontSize = 25.sp,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
            }
        }
    }
}

