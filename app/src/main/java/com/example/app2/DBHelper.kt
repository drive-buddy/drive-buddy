package com.example.app2

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DBHelper {
    private var TAG: String? = "DB"
    var db: FirebaseFirestore = Firebase.firestore

//    CREATE
    public fun addUser(data: HashMap<String, String?>) {
        addDocument("users", data)
    }

    public fun addOrder(data: HashMap<String, String?>) {
        addDocument("orders", data)
    }

    private fun addDocument(collection: String, data: Any) {
        db.collection(collection)
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

//    READ
    fun readDocumentsAll(collection: String) {
        db.collection(collection)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents.", e)
            }
    }
}