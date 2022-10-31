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

    // retrieve a single user based on id and call the callback on user data
    fun getUser(userID: String, callback: (data: Map<String, Any?>) -> Unit) {
        db.collection("users").document(userID)
            .get()
            .addOnSuccessListener { document -> callback(document.data!!) }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting user by id", e)
            }
    }

    fun getOrders(
        callback: (data: QuerySnapshot) -> Unit,
        from: String? = null,
        to: String? = null,
        date: Date? = null,
        seatsNum: Int? = null,
        isSmoking: Boolean? = null,
        allowsPets: Boolean? = null,
        allowsLuggage: Boolean? = null,
        limit: Int = 20
    ) {
        // create the query
        val driversRef = db.collection("orders")
        var query = driversRef.limit(limit.toLong())

        if (from != null)
            query = query.whereEqualTo("from", from)

        if (to != null)
            query = query.whereEqualTo("to", to)

        if (date != null)
            query = query.whereEqualTo("date", date)

        if (seatsNum != null)
            query = query.whereEqualTo("seats", seatsNum)

        if (allowsLuggage != null)
            query = query.whereEqualTo(FieldPath.of("preferences", "allowsLuggage"), allowsLuggage)

        if (allowsPets != null)
            query = query.whereEqualTo(FieldPath.of("preferences", "allowsPets"), allowsPets)

        if (isSmoking != null)
            query = query.whereEqualTo(FieldPath.of("preferences", "isSmoking"), isSmoking)

        // execute the query
        query.get()
            .addOnSuccessListener { documents -> callback(documents) }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting orders.", e) }
    }
}