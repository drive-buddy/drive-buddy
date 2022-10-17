package com.example.app2

import androidx.compose.runtime.Composable

open class DataValidator () {

}

class DataValidatorPageOne (userHashMap : HashMap<String, String>) : DataValidator() {
    val userEmail : String? = userHashMap["email"]
    val userName : String?= userHashMap["userName"]
    val userPassword1 : String? = userHashMap["userPassword1"]
    val userPassword2 : String? = userHashMap["userPassword2"]

//    @Composable
    fun checkEmail() : Boolean {
        if (userEmail?.isEmpty() == true)
        {
            return (false)
        }
        val checker : Regex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

        return (checker.matches(userEmail.toString()))
    }

    fun checkUsername() : Boolean {
        if (userName?.isEmpty() == true)
        {
            return (false)
        }

        if (userName?.length!! < 6)
        {
            return (false)
        }

        return (true)
    }

    fun checkPassword() : Boolean {
        if (userPassword1?.isEmpty() == true || userPassword2?.isEmpty() == true)
        {
            return (false)
        }

        if (userPassword1 != userPassword2)
        {
            return (false)
        }

        return (true)
    }
}