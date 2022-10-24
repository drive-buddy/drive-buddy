package com.example.app2

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpProcess : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                    val allInfo : HashMap<String, String?> =
                        intent.getSerializableExtra("userHashMap", HashMap::class.java) as HashMap<String, String?>

                    for ((key, value) in allInfo) {
                        Log.i("AnyWay", "$key : $value")
                    }

                    val userEmail : String? = allInfo["email"]
                    val userPassword : String? = allInfo["userPassword"]
                    val userType : String? = allInfo["type"]

                    if (userEmail!!.isEmpty() || userPassword!!.isEmpty())
                    {
                        when (userType)
                        {
                            "passenger" -> {
                                val navigate1 = Intent(this@SignUpProcess, Passenger1::class.java)
                                startActivity(navigate1)
                            }
                            "driver" -> {
                                val navigate1 = Intent(this@SignUpProcess, Driver1::class.java)
                                startActivity(navigate1)
                            }
                        }
                        finish()
                    }
                    else
                    {
                        Log.i("ABOBa", userEmail)
                        Log.i("ABOBa", userPassword)
                        createAccount(userEmail = userEmail, userPassword = userPassword, allInfo = allInfo)
                    }
                }
            }
        }
    }

    private fun createAccount(userEmail : String, userPassword : String, allInfo : HashMap<String, String?>)
    {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val dbEntry : DBHelper = DBHelper()
                    allInfo["uid"] = auth.currentUser?.uid
                    dbEntry.addUser(allInfo)
                    val navigate1 = Intent(this@SignUpProcess, No_result::class.java)
                    startActivity(navigate1)
                    finish()
                } else {
                    // If sign up fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    val temp : List<String> = task.exception
                        .toString()
                        .split(": ")
                    val errorMsg = temp[1]
                    Toast.makeText(baseContext, errorMsg,
                        Toast.LENGTH_SHORT).show()
                    when (allInfo["type"])
                    {
                        "passenger" -> {
                            val navigate1 = Intent(this@SignUpProcess, Passenger1::class.java)
                            startActivity(navigate1)
                        }
                        "driver" -> {
                            val navigate1 = Intent(this@SignUpProcess, Driver1::class.java)
                            startActivity(navigate1)
                        }
                    }
                    finish()
                }
            }
    }
}