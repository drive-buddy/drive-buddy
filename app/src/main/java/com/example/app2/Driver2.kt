package com.example.app2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

                    var name by rememberSaveable { mutableStateOf("") }
                    var surname by rememberSaveable { mutableStateOf("") }
                    var gender by rememberSaveable { mutableStateOf("") }
                    var birthDate by rememberSaveable { mutableStateOf("") }
                    var phoneNr by rememberSaveable { mutableStateOf("") }
                    var carModel by rememberSaveable { mutableStateOf("") }
                    var carPlate by rememberSaveable { mutableStateOf("") }
                    var yearOfExp by rememberSaveable { mutableStateOf("") }

                    var validateName by rememberSaveable { mutableStateOf(true) }
                    var validateSurname by rememberSaveable { mutableStateOf(true) }
                    var validateGender by rememberSaveable { mutableStateOf(true) }
                    var validateBirthDate by rememberSaveable { mutableStateOf(true) }
                    var validatePhoneNr by rememberSaveable { mutableStateOf(true) }
                    var validateCarModel by rememberSaveable { mutableStateOf(true) }
                    var validateCarPlate by rememberSaveable { mutableStateOf(true) }
                    var validateYearOfExp by rememberSaveable { mutableStateOf(true) }

                    val validateNameError = "Please input a valid name"
                    val validateSurnameError = "Please input a valid Surname"
                    val validateGenderError = "Please input a valid Gender: M/F"
                    val validateBirthDateError = "Please input birth date in required form"
                    val validatePhoneNrError = "Please input a valid phone number"
                    val validateCarModelError = "Please input a valid car model"
                    val validateCarPlateError = "Please input a valid car plate"
                    val validateYearOfExpError = "Please input years of experience"

                    fun validateData(name: String,
                                     surname: String,
                                     gender: String,
                                     birthDate: String,
                                     phoneNr: String,
                                     carModel: String,
                                     carPlate: String,
                                     yearOfExp: String): Boolean{

                        val genderRegex = "(?=.*[MF]).{1,}".toRegex()

                        val birthDateRegex = ("(0[1-9]|1[0-2])\\/(0[1-9]|1\\d|2" +
                                "\\d|3[01])\\/(19|20)\\d{2}").toRegex()

                        val carPlateRegex = ("(^[A-Z]{2}[0-9]{2}\\s?[A-Z]{3})" +
                                "|(^[A-Z][0-9]{1,3}[A-Z]{3}|(^[A-Z]{3}[0-9]{1,3}[A-Z])|" +
                                "(^[0-9]{1,4}[A-Z]{1,2})|(^[0-9]{1,3}[A-Z]{1,3})|" +
                                "(^[A-Z]{1,2}[0-9]{1,4})|(^[A-Z]{1,3}[0-9]{1,3})|" +
                                "(^[A-Z]{1,3}[0-9]{1,4})|(^[0-9]{3}[DX]{1}[0-9]{3})").toRegex()

                        val yearOfExpRegex = "(?=.*[0-9]).{1,}".toRegex()


                        validateName = name.isNotBlank()
                        validateSurname = surname.isNotBlank()
                        validateGender = genderRegex.matches(gender)
                        validateBirthDate = birthDateRegex.matches(birthDate)
                        validatePhoneNr = Patterns.PHONE.matcher(phoneNr).matches()
                        validateCarModel = carModel.isNotBlank()
                        validateCarPlate = carPlateRegex.matches(carPlate)
                        validateYearOfExp = yearOfExpRegex.matches(yearOfExp)

                        return validateName && validateSurname && validateGender
                                && validateBirthDate && validatePhoneNr && validateCarModel
                                && validateCarPlate && validateYearOfExp
                    }

                    fun register(
                        name: String,
                        surname: String,
                        gender: String,
                        birthDate: String,
                        phoneNr: String,
                        carModel: String,
                        carPlate: String,
                        yearOfExp: String
                    ){
                        if(validateData(name, surname, gender, birthDate,
                                phoneNr, carModel, carPlate, yearOfExp)){
//                            button logic required
                        }
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
                                hint = "DD/MM/YYYY",
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

