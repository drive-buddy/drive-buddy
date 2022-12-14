package com.example.app2.helperfiles

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class DBHelper {
    private var TAG: String? = "DB"
    var db: FirebaseFirestore = Firebase.firestore
    var prefs: SharedPreferences?

    constructor(prefs: SharedPreferences? = null) {
        this.prefs = prefs
    }

    //    CREATE
    fun addUser(data: HashMap<String, String?>) {
        addDocument("users", data) {}
    }

    fun addOrder(data: HashMap<String, String?>) {
        addDocument("orders", data) { }
    }

    fun addPassengerRequest(data: HashMap<String, Any?>, callback: (String) -> Unit) {
        addDocument("passengerRequests", data) { callback.invoke(it) }
    }

    fun addDriverOffer(data: HashMap<String, Any?>, callback: (String) -> Unit) {
        addDocument("driverOffers", data) { callback.invoke(it) }
    }

    private fun addDocument(collection: String, data: Any, callback: (String) -> Unit) {
        db.collection(collection)
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    callback.invoke(documentReference.id).toString()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    //    READ
    fun getDocumentsAll(collection: String, callback: (data: QuerySnapshot) -> Unit) {
        db.collection(collection)
            .limit(50)
            .get()
            .addOnSuccessListener { result -> callback(result) }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting documents.", e)
        }
    }

    // retrieve a single user based on id and call the callback on user data
    fun getUser(userID: String,
                callback: (data: Map<String, Any?>) -> Unit,
                storeAsGlobalState: Boolean = false) {
        db.collection("users").document(userID)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if (storeAsGlobalState) {
                        val userData: MutableMap<String, Any>? = document.data

                        if (userData != null) {
                            for ((key, value) in prefs!!.all) {
                                prefs!!.edit().remove(key.toString()).commit()
                            }

                            for ((key, value) in userData) {
                                prefs?.edit()?.putString(key.toString(), value.toString())
                                    ?.commit()
                            }
                        }
                    }

                    callback(document.data as Map<String, Any?>)
                }
            }
    }

    // retrieve a single user based on id and call the callback on user data
    fun getPassengerRideInfo(rideID: String,
                callback: (data: HashMap<String, Any?>) -> Unit) {
        db.collection("passengerRequests")
            .document(rideID)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    callback.invoke(document.data as HashMap<String, Any?>)
                }
            }
    }

    // set as active order
    fun setCurrentOrder(userEmail: String, rideID : String) {
        db
            .collection("users")
            .whereEqualTo("email", userEmail)
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        db.collection("users")
                            .document(document.id)
                            .update("activeOrderID", rideID)

                        Log.i(TAG, "${document.id} => ${document.data}")
                    }
                }
            }
    }

    // retrieve a single user based on id and call the callback on user data
    fun removePassengerRequest(rideID: String) {
        db.collection("passengerRequests")
            .document(rideID)
            .delete()
    }

    fun getIdByEmail(email: String, callback: (data: String) -> Unit) {
        db
            .collection("users")
//            .document(userID)
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        Log.i(TAG, "${document.id} => ${document.data}")
                        callback.invoke(document.id as String)
                    }
                }
                else
                {
                    Log.e(TAG, "document null")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting user by id", e)
            }
    }

    // retrieve a single user based on id and call the callback on user data
    fun getUser(email: String, callback: (data: Map<String, Any?>) -> Unit) {
        db
            .collection("users")
//            .document(userID)
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        Log.i(TAG, "${document.id} => ${document.data}")
                        callback.invoke(document.data as Map<String, Any?>)
                    }
                }
                else
                {
                    Log.e(TAG, "document null")
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting user by id", e)
            }
    }

    // get currently connected user
    fun getCurrentUser(): String {
        val user = Firebase.auth
        var userEmail : String = ""
        if (user.currentUser !== null) {
            userEmail = user.currentUser!!.email.toString()
            Log.i(TAG, userEmail);
        }
        else {
            Log.i(TAG, "user is null in getCurrentUser()");
        }
        return (userEmail);
    }

    // sign out
    fun signout() {
        Firebase.auth.signOut()
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
        from: String? = null,
        to: String? = null,
        date: Date? = null,
        seatsNum: Int? = null,
        isSmoking: Boolean? = null,
        allowsPets: Boolean? = null,
        allowsLuggage: Boolean? = null,
        limit: Int = 20,
        callback: (data: QuerySnapshot) -> Unit,
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
            .addOnSuccessListener { documents -> callback.invoke(documents) }
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
    fun addPassengerToRide(rideID: String, spot : Int, passengerEmail: String) {
        val ride: DocumentReference = db.collection("driverOffers").document(rideID)

        setCurrentOrder(userEmail = passengerEmail, rideID = rideID)

        ride.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
//                    val passengerRef: DocumentReference = db.collection("users").document(passengerId)

                    ride.update("passenger$spot", passengerEmail)
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting order by id.", e) }
    }

    // REMOVE
    fun removePassengerFromRide(collectionID: String, rideID: String, spot : Int, passengerEmail: String) {
        val ride: DocumentReference = db.collection(collectionID).document(rideID)

        setCurrentOrder(userEmail = passengerEmail, "")

        ride.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    ride.update("passenger$spot", "")
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting order by id.", e) }
    }

    // REMOVE ORDER
    fun removeOrder(collectionID: String, rideID: String) {
        val ride: DocumentReference = db.collection(collectionID).document(rideID)

        ride.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    ride.delete()
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting order by id.", e) }
    }

    // UPDATE
    fun driverAcceptRide(rideId: String, passengerId: String) {
        val ride: DocumentReference = db.collection("orders").document(rideId)

        ride.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val passengerRef: DocumentReference = db.collection("users").document(passengerId)

                    ride.update("passengers", FieldValue.arrayUnion(passengerRef))
                }
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error getting order by id.", e) }
    }
}