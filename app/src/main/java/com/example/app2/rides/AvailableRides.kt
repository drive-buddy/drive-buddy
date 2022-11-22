package com.example.app2.rides


import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.Schedule_ride
import com.example.app2.drawer.DrawerLayout
import com.example.app2.helperfiles.*
import com.example.app2.rides.booking.DriverBookRide
import com.example.app2.rides.booking.PassengerBookRide
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import gen._base._base_java__assetres.srcjar.R.id.info
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class AvailableRides : ComponentActivity() {
    private val viewModel: RidesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // A surface container using the 'background' color from the theme
                    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
                    val searchTextState: String by sharedViewModel.searchTextState
//                    AvailableRides(
//                        sharedViewModel = sharedViewModel,
//                        searchAppBarState = searchAppBarState,
//                        searchTextState = searchTextState
//                    )
                    DrawerLayout(
                        contentFun = {
                            Column(modifier = Modifier
//                                .padding(2.dp)
//                                .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = "Available Rides:",
                                    fontSize = 28.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(top = 20.dp, start = 30.dp)
                                        .offset(1.dp, 1.dp)
                                )
                                val dataOrException = viewModel.data.value

                                InfoList(dataOrException) {
                                    val dbEntry : DBHelper = DBHelper(null)
                                    val userEmail : String = dbEntry.getCurrentUser()
                                    Log.i("infoAR", it.toString())
                                    dbEntry.getUser(userEmail) { document ->
                                        if (document["type"] == "passenger")
                                        {
                                            val navigate = Intent(this@AvailableRides, PassengerBookRide::class.java)
                                            navigate.putExtra("rideId", it.id)
//                                            navigate.putExtra("driver", it.driver)
                                            navigate.putExtra("passenger1", it.passenger1)
                                            navigate.putExtra("passenger2", it.passenger2)
                                            navigate.putExtra("passenger3", it.passenger3)
                                            startActivity(navigate)
                                            finish()
                                        }
                                        else if (document["type"] == "driver") {
                                            val navigate = Intent(this@AvailableRides, DriverBookRide::class.java)
                                            navigate.putExtra("rideId", it.id)
//                                            navigate.putExtra("driver", it.driver)
//                                            navigate.putExtra("passenger1", it.passenger1)
                                            startActivity(navigate)
                                            finish()
                                        }
                                    }
                                }
                            }
                        },
                        floatingActionButtonFun = {
                            Button(onClick = {
                                val navigate = Intent(this@AvailableRides, Schedule_ride::class.java)
                                startActivity(navigate)
                            },
                                modifier = Modifier.size(70.dp),
                                shape = CircleShape,
                                border = BorderStroke(2.dp, Color.White),
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    modifier = Modifier.fillMaxSize(),
                                    contentDescription = "add",
                                    tint = Color.White)
                            }
                        }
                    )
                }
            }
        }
    }

    fun getCorrectUser(driver : Infos, users : List<User>?) : User {
        Log.i("Simion", driver.toString())
        users?.forEach() {
            if (it.email == driver.driver)
            {
                return (it)
            }
        }
        return (User())
    }

    @Composable
    fun InfoList(
        dataOrException: DataOrException<List<Infos>, List<User>, Exception>,
        buttonBehavior: (info : Infos) -> Unit,
    ) {
        val items = dataOrException.data
        val users : List<User>? = dataOrException.user
//        var userData : List<Map<String, Any?>> = listOf()

//        val dbEntry : DBHelper = DBHelper(null)
        Log.d("Simion", users.toString())

        items?.let {
            LazyColumn(state = rememberLazyListState()) {
                items(items)
                { data ->
                        Log.d("CardList", "HomeList: add data $data")
                        InfoRow(
                            info = data,
                            user = getCorrectUser(data, users),
                            buttonBehavior = buttonBehavior,
                        )
                    }
                }
            }
        val e = dataOrException.e
        e?.let {
            Text(
                text = e.message!!,
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressBar(
                isDisplayed = viewModel.loading.value
            )
        }
    }
}

class RidesRepository @Inject constructor(
) {
    suspend fun getInfoFromFirestore(type : String): DataOrException<List<Infos>, List<User>, Exception> {
        val dataOrException = DataOrException<List<Infos>, List<User>, Exception>()
//        var users = listOf<User>()
        try {
            if (type == "passenger") {
                dataOrException.data = queryRidesForPassenger().get().await().map { document ->
                    document.toObject(Infos::class.java)
                }
                dataOrException.user = getUserInfoForPassenger().get().await().map { document ->
                    document.toObject(User::class.java)
                }
//                Log.i("users", users.toString())
            } else {
                dataOrException.data = queryRidesForDriver().get().await().map { document ->
                    document.toObject(Infos::class.java)
                }
            }

        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }

    private suspend fun queryRidesForPassenger() = FirebaseFirestore.getInstance()
        .collection("driverOffers")
        .orderBy("to", Query.Direction.ASCENDING)

    private suspend fun getUserInfoForPassenger() = FirebaseFirestore.getInstance()
        .collection("users")
//        .whereEqualTo("email", email)
        .orderBy("email", Query.Direction.ASCENDING)

    private suspend fun queryRidesForDriver() = FirebaseFirestore.getInstance()
        .collection("passengerRequests")
        .orderBy("to", Query.Direction.ASCENDING)
}

@HiltViewModel
class RidesViewModel @Inject constructor(
    private val repository: RidesRepository
): ViewModel() {
    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Infos>, List<User>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            listOf(),
            Exception("")
        )
    )

    init {
        val DBEntry : DBHelper = DBHelper()
        DBEntry.getUser(DBEntry.getCurrentUser()) {
            it ->
            getRides(it["type"] as String)
        }
    }

    private fun getRides(type : String) {
        viewModelScope.launch {
            loading.value = true
            data.value = repository.getInfoFromFirestore(type)
            loading.value = false
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun CircularProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        Card(modifier = Modifier
            .background(color = Color.Transparent),
        ){
            CircularProgressIndicator(modifier = Modifier
                .background(color = Color.Transparent)
                .width(100.dp)
                .height(100.dp),
                color = Color.Red,
                strokeWidth = 2.dp)
        }
    }
}

//interface PrepareRidesCallback {
//    fun OnCompleted(tasks: List<Infos>) : MutableList<Infos>
//}
//
//fun prepareRides(callback: PrepareRidesCallback) {
//    val dbEntry : DBHelper = DBHelper()
//    val userEmail : String = dbEntry.getCurrentUser()
//    var userInfo : Map<String, Any?> = HashMap<String, Any>()
//    var userRides : MutableList<Infos> = mutableListOf()
//    dbEntry.getUser(userEmail) {
//            document ->
//        userInfo = document
//        if (userInfo["type"] == "passenger")
//        {
//            dbEntry.getOrders() { orders ->
//                callback.OnCompleted(processOrders(orders))
//            }
//        }
//        else if (userInfo["type"] == "driver")
//        {
////            dbEntry.addDriverOffer(result)
//        }
//        else
//        {
//            Log.e("error", "type not defined")
//            Log.i("info2", userInfo.toString())
//        }
//    }
//}
//fun processOrders(orders: QuerySnapshot): MutableList<Infos> {
//    val userRides : MutableList<Infos> = mutableListOf()
//
//    for (entry in orders.documents) {
//        userRides.add(
//            Infos(
////                          date = entry.data?.entries["date"] as String?,
//                price = entry.data?.get("price") as String?,
////                            time = entryData["time"] as String?,
//                from = entry.data?.get("from") as String?,
//                to = entry.data?.get("to") as String?,
//                nrOfSeats = entry.data?.get("nrOfSeats") as String?,
//                name = "test",
//                surname = "test",
//                carModel = "test",
//                license = "test",
//            )
//        )
//        Log.i("test", "${entry.data?.entries!!}")
//    }
//    Log.i("here1", "$userRides")
//    return (userRides)
//}
//
//@Composable
//fun AvailableRides(
//    sharedViewModel: SharedViewModel,
//    searchAppBarState: SearchAppBarState,
//    searchTextState: String
//) {
////    Scaffold(topBar = {
////        CustomTopAppBar(
////            sharedViewModel = sharedViewModel,
////            searchAppBarState = searchAppBarState,
////            searchTextState = searchTextState
////        )
////    },
////    content = {
////        padding ->
//////        InfoList(infoss)
////    }
////    )
//    DrawerLayout(contentFun = {
//        Text(
//            text = "Available Rides:",
//            fontSize = 28.sp,
//            fontFamily = FontFamily.SansSerif,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.White,
//            modifier = Modifier
//                .padding(top = 20.dp, start = 30.dp)
//                .fillMaxWidth(0.5f)
//                .offset(1.dp, 1.dp)
//        )
//    })
////    Surface(
////        modifier = Modifier
////            .fillMaxWidth()
////            .offset(0.dp, 81.dp)
////            .fillMaxHeight(),
////        color = Color(0xFFEE5252),
////    ) {
////
////
////        LazyColumnDemo()
////    }
//
//}