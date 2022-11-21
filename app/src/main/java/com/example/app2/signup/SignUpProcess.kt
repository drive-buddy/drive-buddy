package com.example.app2.signup

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.app2.No_result
import com.example.app2.driver.Driver2
import com.example.app2.driver.Driver3
import com.example.app2.passenger.Passenger1
import com.example.app2.passenger.Passenger2
import com.example.app2.passenger.Passenger3
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpProcess : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth and check if the user is signed in
        auth = Firebase.auth
        setContent {
            App2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userEmail : String? = intent.getStringExtra("email")
                    val userPassword : String? = intent.getStringExtra("password")
                    val userType : String? = intent.getStringExtra("type")

                    if (userEmail!!.isEmpty() || userPassword!!.isEmpty())
                    {
                        when (userType)
                        {
                            "passenger" -> {
                                val navigate1 = Intent(this@SignUpProcess, Passenger2::class.java)
                                startActivity(navigate1)
                            }
                            "driver" -> {
                                val navigate1 = Intent(this@SignUpProcess, Driver2::class.java)
                                startActivity(navigate1)
                            }
                        }
                        finish()
                    }
                    else
                    {
                        var docId : String = ""
                        Log.i("ABOBa", userEmail)
                        Log.i("ABOBa", userPassword)
                        createAccount(userEmail = userEmail, userPassword = userPassword)

                        when (userType)
                        {
                            "passenger" -> {
                                val navigate1 = Intent(this@SignUpProcess, Passenger3::class.java)
                                startActivity(navigate1)
                            }
                            "driver" -> {
                                val navigate1 = Intent(this@SignUpProcess, Driver3::class.java)
                                startActivity(navigate1)
                            }
                        }
                        finish()
                    }
                }
            }
        }
    }

    private fun createAccount(userEmail : String, userPassword : String)
    {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val navigate1 = Intent(this@SignUpProcess, No_result::class.java)
                    startActivity(navigate1)
                    finish()
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                    // ERROR
                    // TO DO CHANGE Passenger1 from debugging
                    val navigate1 = Intent(this@SignUpProcess, Passenger1::class.java)
                    startActivity(navigate1)
                    finish()
                }
            }
    }
}