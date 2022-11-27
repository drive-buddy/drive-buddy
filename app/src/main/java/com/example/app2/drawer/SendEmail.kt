package com.example.app2.drawer

import com.example.app2.ui.theme.App2_2Theme
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.app2.ui.theme.App2_2Theme
import androidx.compose.ui.graphics.Color
import com.example.app2.TopBar


class SendEmail : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                backgroundColor = Color(0xFFEE5252),
                                title = {
                                    Text(
                                        text = "Support",
                                        color = Color.White,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            )
                        }
                    ) {
                        openEmailer()
                    }
                }
            }
        }
    }
}

@Composable
fun openEmailer() {

    // creating variables for URL
    val senderEmail = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailSubject = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailBody = remember {
        mutableStateOf(TextFieldValue())
    }

    // a variable for a context
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = senderEmail.value,
            onValueChange = { senderEmail.value = it },
            placeholder = { Text(text = "dmoraru@pentalog.com") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = emailSubject.value,
            onValueChange = { emailSubject.value = it },
            placeholder = { Text(text = "Add a subject") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = emailBody.value,
            onValueChange = { emailBody.value = it },
            placeholder = { Text(text = "Enter email body") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

            // an intent to send an email
            val i = Intent(Intent.ACTION_SEND)

            // passing email address, email subject and email body
            val emailAddress = arrayOf(senderEmail.value.text)
            i.putExtra(Intent.EXTRA_EMAIL,emailAddress)
            i.putExtra(Intent.EXTRA_SUBJECT,emailSubject.value.text)
            i.putExtra(Intent.EXTRA_TEXT,emailBody.value.text)

            // setting type of intent
            i.setType("message/rfc822")

            // starting our activity to open email application.
            ctx.startActivity(Intent.createChooser(i,"Choose an Email client : "))},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
        ) {
            Text(
                text = "Send Email",
                modifier = Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}
