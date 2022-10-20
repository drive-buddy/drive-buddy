package com.example.app2

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import com.example.app2.R.drawable
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.compose.ui.text.TextStyle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    var db: DBHelper = DBHelper()
    // Firebase instance variables
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth and check if the user is signed in
        auth = Firebase.auth

//        Log.i("myTag", auth.currentUser)
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, StartPage::class.java))
            finish()
            return
        }
        else
        {
            // Update if user's account has been disabled
            // Use this to have updates when the account is
            // disabled ;)
            auth.currentUser!!.reload()
                .addOnCompleteListener(this) { task ->
                    if (task.isComplete) {
                        // Signed in, launch the  activity
                        val navigate1 = Intent(this@MainActivity, No_result::class.java)

                        startActivity(navigate1)
                        finish()
                    } else {
                        // Signed in, launch the  activity
                        val navigate1 = Intent(this@MainActivity, Sign_in::class.java)

                        startActivity(navigate1)
                        finish()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in.
        //        Log.i("myTag", auth.currentUser)
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, StartPage::class.java))
            finish()
            return
        }
        else {
            // Update if user's account has been disabled
            // Use this to have updates when the account is
            // disabled ;)
            auth.currentUser!!.reload()
                .addOnCompleteListener(this) { task ->
                    if (task.isComplete) {
                        // Signed in, launch the  activity
                        val navigate1 = Intent(this@MainActivity, No_result::class.java)

                        startActivity(navigate1)
                        finish()
                    } else {
                        // Signed in, launch the  activity
                        val navigate1 = Intent(this@MainActivity, Sign_in::class.java)

                        startActivity(navigate1)
                        finish()
                    }
                }
        }
    }

    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }


    private fun checkCurrentUser() {
        // [START check_current_user]
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }

    private fun getUserProfile() {
        // [START get_user_profile]
        val user = Firebase.auth.currentUser

        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        // [END get_user_profile]
    }
}