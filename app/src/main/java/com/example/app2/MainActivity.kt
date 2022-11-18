package com.example.app2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.app2.helperfiles.DBHelper
import com.example.app2.rides.AvailableRides
import com.example.app2.signin.Sign_in
import com.google.firebase.auth.FirebaseAuth
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
                        val navigate1 = Intent(this@MainActivity, AvailableRides::class.java)

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
                        val navigate1 = Intent(this@MainActivity, AvailableRides::class.java)

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