package com.example.app2.passenger

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.*
import com.example.app2.R
import com.example.app2.helperfiles.CheckBoxDemo
import com.example.app2.signup.Choose
import com.example.app2.ui.theme.App2_2Theme

class Passenger1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val userHashMap : HashMap<String, String> = HashMap<String, String> ()

                    var userName by rememberSaveable { mutableStateOf("") }
                    var userEmail by rememberSaveable { mutableStateOf("") }
                    var userPassword by rememberSaveable { mutableStateOf("") }
                    var userPasswordConfirm by rememberSaveable { mutableStateOf("") }
                    var isPasswordVisible by remember { mutableStateOf(false) }


                    val icon = if(isPasswordVisible){
                        painterResource(id = R.drawable.ic_baseline_visibility_24)
                    }
                    else{
                        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
                    }


                    var validateName by rememberSaveable { mutableStateOf(true) }
                    var validateEmail by rememberSaveable { mutableStateOf(true) }
                    var validatePassword by rememberSaveable { mutableStateOf(true) }
                    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
                    var validatePasswordsEqual by rememberSaveable { mutableStateOf(true) }

                    val validateNameError = "Please input a valid name"
                    val validateEmailError = "The format of the email doesn't seem right"
                    val validatePasswordError = "Must mix capital and non-capital letters"
                    val validatePasswordsEqualsError = "Password must be equal"

                    fun validateData(name: String,
                                     email: String,
                                     password: String,
                                     confirmPassword: String): Boolean{
                        val passwordRegex = "(?=.*[a-z])(?=.*[A-Z]).{1,}".toRegex()

                        validateName = name.isNotBlank()
                        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        validatePassword = passwordRegex.matches(password)
                        validateConfirmPassword = passwordRegex.matches(confirmPassword)
                        validatePasswordsEqual = password == confirmPassword

                        return validateName && validateEmail && validatePassword
                                && validateConfirmPassword && validatePasswordsEqual

                    }

                    fun register(
                        name: String,
                        email: String,
                        password: String,
                        confirmPassword: String
                    ){
                        if(validateData(name, email, password, confirmPassword)){
                            val navigate1 = Intent(this@Passenger1, Passenger2::class.java)

//                                // setter
                            navigate1.putExtra("email", userHashMap["email"])
                            navigate1.putExtra("username", userHashMap["userName"])
                            navigate1.putExtra("password", userHashMap["userPassword"])
                            navigate1.putExtra("type", "passenger")
//
//                                // getter
//                                navigate1.getStringExtra("hello")

                            startActivity(navigate1)
                            finish()
                            }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp),
                    ) {

                        Column {
                            Text(
                                text = "Create",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                modifier = Modifier.offset(x = 40.dp)
                            )
                            Text(
                                text = "Account",
                                fontSize = 42.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFEE5252),
                                modifier = Modifier.offset(x = 40.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp)
                                .verticalScroll(rememberScrollState())
                                .weight(weight = 1f, fill = false),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                        ) {

                            PrettyBar(
                                hint = "mymail@mail.com",
                                type = "Email",
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),
                                activeVariable = userEmail,
                                onVarChange = {
                                    userEmail = it
                                },
                                KeyboardSettings = KeyboardOptions(keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next),
                                errorMessage = validateEmailError,
                                showError = !validateEmail
                            )

                            userHashMap["email"] = userEmail.trim()

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),
                                type = "Username",
                                activeVariable = userName,
                                onVarChange = {
                                    userName = it
                                },
                                errorMessage = validateNameError,
                                showError = !validateName
                            )

                            userHashMap["userName"] = userName.trim()

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),

                                type = "Password",
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
                                errorMessage = validatePasswordError,
                                showError = !validatePassword
                            )

                            userHashMap["userPassword"] = userPassword.trim()

                            Spacer(Modifier.height(15.dp))

                            PrettyBar(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(330.dp),
                                type = "Confirm password",
                                activeVariable = userPasswordConfirm,
                                onVarChange = {
                                    userPasswordConfirm = it
                                },

                                KeyboardSettings = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done),

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
                                errorMessage = if(!validateConfirmPassword) validatePasswordError
                                else validatePasswordsEqualsError,
                                showError = !validatePasswordsEqual || !validateConfirmPassword
                            )

                            userHashMap["userPasswordConfirm"] = userPasswordConfirm.trim()

                            Spacer(Modifier.height(15.dp))

                            Row (
                                horizontalArrangement = Arrangement.Start
                                    )
                            {
                                CheckBoxDemo()
                                Text(
                                    text = "I accept all the terms and conditions\n" +
                                            "of Privacy Policy",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF888686),
                                    modifier = Modifier.offset(y = 10.dp)
                                )
                            }
                        }

                            }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    )
                    {
                        Button(
                            onClick = {
                                val navigate = Intent(this@Passenger1, Choose::class.java)
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
                                val validator : DataValidatorPageOne = DataValidatorPageOne(userHashMap)

//                                if (!validator.checkEmail())
//                                {
//                                    Log.i("myTag", "mail issue")
//                                    return@Button
//                                }
//                                else if (!validator.checkUsername())
//                                {
//                                    Log.i("myTag", "username issue")
//                                    return@Button
//                                }
//
//                                else if (!validator.checkPassword())
//                                {
//                                    Log.i("myTag", "password issue")
//                                    return@Button
//                                },

                                register(userName, userEmail, userPassword ,userPasswordConfirm)
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


