package com.example.app2

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

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
    fun readDocumentsAll(collection: String): Array<Any> {
        var returnedDocuments: Array<Any> = arrayOf<Any>()

        db.collection(collection)
            .limit(50)
            .get()
            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }

                returnedDocuments += result
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents.", e)
            }

        return returnedDocuments
    }

    // retrieve a single user based on id
    fun getUser(userID: String, callback: (data: Map<String, Any?>) -> Unit) {
        db.collection("users").document(userID)
            .get()
            .addOnSuccessListener { document -> callback(document.data!!) }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting user by id", e)
            }
    }

    fun searchOrders(
        from: String,
        to: String,
        date: Date,
        seatsNum: Int = -1, // if -1 then doesn't matter
        isSmoking: Boolean = false,
        allowsPets: Boolean = false,
        allowsLuggage: Boolean = true
    ) : QuerySnapshot? {
        var returnedDocuments: QuerySnapshot? = null

        // create the query
        val driversRef = db.collection("orders")
        var query = driversRef
                    .whereEqualTo("from", from)
                    .whereEqualTo("to", to)
                    .whereEqualTo("date", date)
                    .whereEqualTo("isSmoking", isSmoking)
                    .whereEqualTo("allowsPets", allowsPets)
                    .whereEqualTo("allowsLuggage", allowsLuggage)

        if (seatsNum != -1)
            query = query.whereEqualTo("seats", seatsNum)

        // execute the query
        query.get()
            .addOnSuccessListener { documents ->
                returnedDocuments = documents
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting orders.", e)
            }

        return returnedDocuments
    }
}