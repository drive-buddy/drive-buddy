package com.example.app2

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
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInProcess : ComponentActivity() {

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
                    val userEmail = intent.getStringExtra("email").toString()
                    val userPassword = intent.getStringExtra("password").toString()

                    if (userEmail.isEmpty() || userPassword.isEmpty())
                    {
                        val navigate1 = Intent(this@SignInProcess, Sign_in::class.java)
                        navigate1.putExtra("signIn_error", "One or more fields are empty")
                        startActivity(navigate1)
                        finish()
                    }
                    else
                    {
                        signIn(userEmail = userEmail, userPassword = userPassword)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Initialize Firebase Auth and check if the user is signed in
        auth = Firebase.auth
        setContent {
            App2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userEmail = intent.getStringExtra("email").toString()
                    val userPassword = intent.getStringExtra("password").toString()

                    if (userEmail.isEmpty() || userPassword.isEmpty())
                    {
                        val navigate1 = Intent(this@SignInProcess, Sign_in::class.java)
                        navigate1.putExtra("signIn_error", "ABOBA: One or more fields is empty.")
                        startActivity(navigate1)
                        finish()
                    }
                    else
                    {
                        signIn(userEmail = userEmail, userPassword = userPassword)
                    }
                }
            }
        }
    }

    private fun signIn(userEmail : String, userPassword : String)
    {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i(ContentValues.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
//                    updateUI(user)
                    val navigate1 = Intent(this@SignInProcess, No_result::class.java)
                    startActivity(navigate1)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                    // ERROR
                    val navigate1 = Intent(this@SignInProcess, Sign_in::class.java)
                    val errorMsg : String = task.exception.toString()
                    navigate1.putExtra("signIn_error", errorMsg)
                    startActivity(navigate1)
                    finish()
                }
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}