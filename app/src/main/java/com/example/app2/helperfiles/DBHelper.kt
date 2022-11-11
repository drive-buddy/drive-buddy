package com.example.app2.helperfiles

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
    fun getDocumentsAll(collection: String, callback: (data: QuerySnapshot) -> Unit){
        db.collection(collection)
            .limit(50)
            .get()
            .addOnSuccessListener { result -> callback(result) }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting documents.", e) }
    }

    // retrieve a single user based on id and call the callback on user data
    fun getUser(userID: String, callback: (data: Map<String, Any?>) -> Unit) {
        db.collection("users").document(userID)
            .get()
            .addOnSuccessListener { document ->
                if (document != null)
                    callback(document.data as Map<String, Any?>)
            }
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

    fun getUsers(
        callback: (data: QuerySnapshot) -> Unit,
        type: String? = null, // driver or passenger
        firstName: String? = null,
        lastName: String? = null,
        username: String? = null,
        email: String? = null,
        gender: Char? = null,
        phoneNumber: String? = null,
        yearsOfXp: Int? = null,
        age: Int? = null,
        rank: Int? = null,
        carModel: String? = null,
        carNumber: String? = null,
        uid: String? = null,
        limit: Int = 20
    ) {
        // create the query
        val driversRef = db.collection("users")
        var query = driversRef.limit(limit.toLong())

        if (type != null && (type.lowercase() == "passenger" || type.lowercase() == "driver"))
            query = query.whereEqualTo("type", type)

        if (firstName != null && firstName.length > 0)
            query = query.whereEqualTo("firstName", firstName)

        if (lastName != null && lastName.length > 0)
            query = query.whereEqualTo("lastName", lastName)

        if (username != null && username.length > 0)
            query = query.whereEqualTo("username", username)

        if (phoneNumber != null && phoneNumber.length > 0)
            query = query.whereEqualTo("phoneNumber", phoneNumber)

        if (email != null && email.length > 0)
            query = query.whereEqualTo("email", email)

        if (carModel != null && carModel.length > 0)
            query = query.whereEqualTo("carModel", carModel)

        if (carNumber != null && carNumber.length > 0)
            query = query.whereEqualTo("carNumber", carNumber)

        if (uid != null && uid.length > 0)
            query = query.whereEqualTo("uid", uid)

        if (yearsOfXp != null)
            query = query.whereEqualTo("yearsOfXp", yearsOfXp)

        if (age != null)
            query = query.whereEqualTo("age", age)

        if (rank != null)
            query = query.whereEqualTo("rank", rank)

        if (gender != null && (gender == 'M' || gender == 'F'))
            query = query.whereEqualTo("gender", gender)

        // execute the query
        query.get()
            .addOnSuccessListener { documents -> callback(documents) }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting orders.", e) }
    }

    fun getOrderPassengers(rideId: String, callback: (data: Any) -> Unit) {
        db.collection("orders")
            .whereEqualTo(FieldPath.documentId(), rideId)
            .get()
            .addOnSuccessListener { ride ->
                if (ride != null) {
                    val passengers = ride.documents[0].data?.get("passengers")

                    if (passengers != null) {
                        callback(passengers)
                    }
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting order by id.", e) }
    }

    // UPDATE
    fun addPassengerToRide(rideId: String, passengerId: String) {
        val ride: DocumentReference = db.collection("orders").document(rideId)

        ride.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val passengerRef: DocumentReference = db.collection("users").document(passengerId)

                    ride.update("passengers", FieldValue.arrayUnion(passengerRef))
                }
            }

//        db.collection("orders")
//            .whereEqualTo(FieldPath.documentId(), rideId)
//            .get()
//            .addOnSuccessListener { ride ->
//                if (!ride.isEmpty) {
//                    val passengers: ArrayList<DocumentReference> = ride.documents[0].data?.get("passengers") as ArrayList<DocumentReference>
//                    val rideRef: DocumentReference = db.document("")
//
//                    // if the passengers field is not null, extend the array
//                    if (passengers != null) {
////                        passengers.add(db.document("users/" + passengerId))
//
//                    }
//                    // else create the array with one value
//                    else {
//
//                    }
//                }
//            }
//            .addOnFailureListener { e -> Log.w(TAG, "Error getting ride by id", e) }
    }
}