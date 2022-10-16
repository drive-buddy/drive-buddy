package com.example.app2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.app2.ui.theme.App2_2Theme

class Passenger1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
                            .offset(y = 140.dp, x = 40.dp),
                    ) {

                        Text(
                            text = "Create",
                            fontSize = 42.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                        Text(
                            text = "Account",
                            fontSize = 42.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFEE5252)
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
                            text = "Email",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "mymail@mail.com",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(
                            text = "Username",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "my_unique_username",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(
                            text = "Password",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "mysecurepassword123",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(
                            text = "Confirm Password",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF888686)
                        )
                        InputBar2(
                            hint = "mysecurepassword123",
                            modifier = Modifier
                                .height(50.dp)
                                .width(330.dp)
//                                .padding(1.dp)

                        )
                        Row ()
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
                                fontSize = 30.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                        Button(
                            onClick = {
                                val navigate1 = Intent(this@Passenger1, Passenger2::class.java)
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

@Composable
fun InputBar3(
    modifier: Modifier = Modifier,
//        .verticalScroll(),
//        .navigationBarsWithImePadding,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {

    val focusManager = LocalFocusManager.current

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
                .shadow(5.dp, CutCornerShape(10))
                .background(color = Color(0xFFFAF7F7), shape = CutCornerShape(10))
                .padding(horizontal = 30.dp, vertical = 12.dp)
                .onFocusChanged {
//                    isHintDisplayed = it != FocusState.isFocused
                },
                keyboardActions = KeyboardActions
                (
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
        )
    }
}

@Composable
fun InputBar2(
    modifier: Modifier = Modifier,
    hint: String = "Input Text",
    onSearch: (String) -> Unit = {}
) {

    val focusManager = LocalFocusManager.current

    var value by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Box (modifier = modifier)
    {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
//                    .padding(16.dp)
                ) {

                    if (value.text.isEmpty()) {
                        Text(text = hint,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()  //<-- Add this
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color(0xFF888686)),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CutCornerShape(10))
                .background(color = Color(0xFFFAF7F7), shape = CutCornerShape(10))
                .padding(horizontal = 30.dp, vertical = 12.dp)
                .onFocusChanged {
//                    isHintDisplayed = it != FocusState.isFocused
                },
            keyboardActions = KeyboardActions
                (
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }

}

@Composable
fun CheckBoxDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}


