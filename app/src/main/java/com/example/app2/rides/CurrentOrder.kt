package com.example.app2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app2.drawer.DrawerLayout
import com.example.app2.helperfiles.DBHelper
import com.example.app2.helperfiles.Infos
import com.example.app2.helperfiles.User
import com.example.app2.rides.AvailableRides
import com.example.app2.ui.theme.App2Theme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class CurrentOrder : ComponentActivity() {
    private val viewModel: CurrentOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App2Theme {
                CurrentOrderContent()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    fun CurrentOrderContent(
        localContext : Context? = this
    ) {
        DrawerLayout (
            localContext = localContext,
            contentFun = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Color(0xFFEE5252)),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Current",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp, 0.dp, 0.dp))
                    Text("Order",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp)
                    )

                    if (viewModel.data.value.data?.id?.isNotEmpty() == true) {
                        if (viewModel.data.value.user!!.isNotEmpty()) {
                            CardOrder(viewModel.data.value.data!!, viewModel.data.value.user!!)
                        } else {
                            CardOrder(viewModel.data.value.data!!, listOf(User()))
                        }
                        Log.i("CurrentRideInfo", viewModel.data.value.data.toString())

                        Spacer(modifier = Modifier.height(20.dp))

                        androidx.compose.material.Button(
                            onClick = {
                                val dbEntry = DBHelper(null)
                                val userEmail = dbEntry.getCurrentUser()

                                dbEntry.getUser(userEmail) { document ->
                                    cancelRide(document["type"] as String, userEmail, viewModel.data.value.data?.id!!)

                                    Toast.makeText(applicationContext, "Ride successfully canceled.", Toast.LENGTH_SHORT).show()

                                    val navigate = Intent(localContext, CurrentOrder::class.java)
                                    localContext?.startActivity(navigate)
                                }

                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(170.dp)
                                .padding(0.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                        ) {
                            Text(
                                text = "Cancel Ride",
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFEE5252)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(50.dp))

                        Text(
                            "No active ride!",
                            fontSize = 40.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }


                }
            }
        )
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    androidx.compose.material3.Text(
//                        " "
//                    )
//                },
//                Modifier.background(Color.Gray),
//                navigationIcon = {
//                    IconButton(onClick = { /* doSomething() */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Menu",
//                            tint = Color.White
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { /* doSomething() */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Go Back",
//                            tint = Color(0xFFEE5252),
//                            modifier = Modifier
//                                .size(30.dp)
//                                .background(color = Color.White, shape = CircleShape)
//                                .border(1.dp, Color.White, shape = CircleShape),
//                        )
//                    }
//                },
//                backgroundColor = Color(0xFFEE5252)
//            )
//        },
//
//    )
    }

    private fun cancelRide(type : String, userEmail: String, rideID: String) {
        val dbEntry = DBHelper()
        if (type == "passenger") {
            if (viewModel.data.value.data?.driver == "") {
                when (userEmail) {
                    viewModel.data.value.data?.passenger3 -> {
                        dbEntry.removePassengerFromRide("passengerRequests", rideID, 3, userEmail)
                    }
                    viewModel.data.value.data?.passenger2 -> {
                        dbEntry.removePassengerFromRide("passengerRequests", rideID, 2, userEmail)

                    }
                    viewModel.data.value.data?.passenger1 -> {
                        dbEntry.removePassengerFromRide("passengerRequests", rideID, 1, userEmail)

                    }
                    else -> {
                        Log.e("cancelRide fun", "Man, you screwed up big time. `userEmail` is not in current ride.")
                    }
                }
            } else {
                when (userEmail) {
                    viewModel.data.value.data?.passenger3 -> {
                        dbEntry.removePassengerFromRide("driverOffers", rideID, 3, userEmail)

                    }
                    viewModel.data.value.data?.passenger2 -> {
                        dbEntry.removePassengerFromRide("driverOffers", rideID, 2, userEmail)

                    }
                    viewModel.data.value.data?.passenger1 -> {
                        dbEntry.removePassengerFromRide("driverOffers", rideID, 1, userEmail)

                    }
                    else -> {
                        Log.e("cancelRide fun", "Man, you screwed up big time. `userEmail` is not in current ride.")
                    }
                }
            }
        } else {
            dbEntry.setCurrentOrder(userEmail = viewModel.data.value.data?.passenger3!!, rideID = "")
            dbEntry.setCurrentOrder(userEmail = viewModel.data.value.data?.passenger2!!, rideID = "")
            dbEntry.setCurrentOrder(userEmail = viewModel.data.value.data?.passenger1!!, rideID = "")
            dbEntry.setCurrentOrder(userEmail = viewModel.data.value.data?.driver!!, rideID = "")

            dbEntry.removeOrder("driverOffers", rideID)
        }
    }

    @Composable
    fun CardOrder(
        info: Infos = Infos(),
        user: List<User> = listOf(User())
    ) {
        val user1 = user.first()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f)
                .padding(20.dp, 0.dp)
                .clickable { },
            elevation = 10.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround

            ) {
                Row (
                    modifier = Modifier
//                        .width(700.dp)
//                        .padding(horizontal = 20.dp)
//                        .weight(0.5f, fill = false)
                        ){
                    Column {
                        Text(
                            "Price: ${info.price} lei",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,

                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                        val tmp = info.date!!.toDate()
                        val tmp2 = SimpleDateFormat("E dd/MM/yyy, HH:mm z").format(tmp)
                        Text(
                            "Date/time:\n${tmp2}",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Column() {
                            Row() {
                                Canvas(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .offset(0.dp, 7.dp),
                                    onDraw = {
                                    drawCircle(color = Color(0xFFEE5252))
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${info.from}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )


                            }
                            androidx.compose.material.Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxHeight(0.03f)
                                    .width(2.dp)
                                    .offset(4.dp, 7.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Row {
                                Canvas(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .offset(0.dp, 7.dp),
                                    onDraw = {
                                    drawCircle(color = Color(0xFFEE5252))
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${info.to}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(50.dp))

                    Column {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_round),
                                contentDescription = "Profile Picture",
                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                            )
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth(0.5f)
//                                .height(15.dp)
//                                .background(
//                                    brush = Brush.horizontalGradient(listOf(Color.Gray, Black)),
//                                    shape = RoundedCornerShape(20.dp)
//                                ),
//                        ){
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center,
//                            ){
//                                androidx.compose.material.Icon(
//                                    imageVector = Icons.Default.Star,
//                                    contentDescription = "Rank"
//                                )
//                                Text(text = "Rank",
//                                    fontSize = 10.sp)
//                            }
//
//                        }
                            Text(
                                text = "${user1.userFirstName}\n${user1.userSurname}",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "${user1.userPhoneNumber}",
                                fontSize = 12.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
                Column {
                    Column {
                        Text(
                            text = "Car model",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .offset(0.dp, 15.dp)
                        )
                        Card(
                            Modifier
                                .size(width = 360.dp, height = 30.dp)
                                .offset(0.dp, 20.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp, 0.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${user1.carModel}",
                                    fontSize = 15.sp, fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                // Card content
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        Text(
                            text = "Number",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .offset(0.dp, 15.dp)
                        )
                        Card(
                            Modifier
                                .size(width = 360.dp, height = 30.dp)
                                .offset(0.dp, 20.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp, 0.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${user1.carNumber}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                // Card content
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        Text(
                            text = "Years of Experience",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .offset(0.dp, 15.dp)
                        )
                        Card(
                            Modifier
                                .size(width = 360.dp, height = 30.dp)
                                .offset(0.dp, 20.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp, 0.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${user1.yearOfExp} years",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                // Card content
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        Text(
                            text = "Nr. of rides",
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .offset(0.dp, 15.dp)
                        )
                        Card(
                            Modifier
                                .size(width = 160.dp, height = 30.dp)
                                .offset(0.dp, 20.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp, 0.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${info.nrOfSeats}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                // Card content
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (info.smoking == "true") {
                        Image(
                            painter = painterResource(R.drawable.smoking_round), // Smoking allowed
                            contentDescription = "Smoking / No Smoking",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.smoking_no_round), // Smoking not allowed
                            contentDescription = "Smoking / No Smoking",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    }
                    if (info.animals == "true"){
                        Image(
                            painter = painterResource(R.drawable.pet_round), // pet_no_round | pet_round
                            contentDescription = "Pet | No Pet",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.pet_no_round),
                            contentDescription = "Pet | No Pet",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    }
                    if (info.luggage == "true") {
                        Image(
                            painter = painterResource(R.drawable.luggage_round), // luggage_round | luggage_no_round
                            contentDescription = "Luggage | No Luggage",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.luggage_no_round),
                            contentDescription = "Smoking / No Smoking",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                                .clip(CircleShape)
                                .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                        )
                    }
                }
            }
        }
    }
}

class CurrentOrderRepository @Inject constructor(
) {
    suspend fun getInfoFromFirestore(type : String, activeOrderID : String):
            CurrentOrderData<Infos, List<User>> {
        val allInfo = CurrentOrderData<Infos, List<User>>()

        allInfo.data = findRide(activeOrderID).get().await().toObject(Infos::class.java)
        Log.i("allData1", allInfo.data.toString())
        if (allInfo.data == null) {
            allInfo.data = findRide2(activeOrderID).get().await().toObject(Infos::class.java)
        }
        Log.i("allData2", allInfo.data.toString())

        allInfo.user = getUserInfo(allInfo.data?.driver.toString()).get().await().map { document ->
            document.toObject(User::class.java)
        }
        return allInfo
    }

    private suspend fun findRide(rideID : String = "") = FirebaseFirestore.getInstance()
        .collection("passengerRequests")
        .document(rideID)

    private suspend fun findRide2(rideID : String = "") = FirebaseFirestore.getInstance()
        .collection("driverOffers")
        .document(rideID)

    private suspend fun getUserInfo(userEmail : String) = FirebaseFirestore.getInstance()
        .collection("users")
        .whereEqualTo("email", userEmail)
//        .orderBy("email", Query.Direction.ASCENDING)
}

data class CurrentOrderData<T1, T2>(
    var data: T1? = null,
    var user: T2? = null,
)

@HiltViewModel
class CurrentOrderViewModel @Inject constructor(
    private val repository: CurrentOrderRepository
): ViewModel() {
    var loading = mutableStateOf(false)
    val data: MutableState<CurrentOrderData<Infos, List<User>>> = mutableStateOf(
        CurrentOrderData(
            Infos(),
            listOf(),
        )
    )

    init {
        val DBEntry : DBHelper = DBHelper()
        DBEntry.getUser(DBEntry.getCurrentUser()) {
                it ->
            if (!(it["activeOrderID"] as String?).isNullOrBlank()) {
                getRides(it["type"] as String, it["activeOrderID"] as String)
            }
        }
    }

//    private fun getImportantEmail(orderID : String) {
//
//    }

    private fun getRides(type : String, activeOrderID : String) {
        viewModelScope.launch {
            loading.value = true
            data.value = repository.getInfoFromFirestore(type, activeOrderID)
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
            androidx.compose.material.CircularProgressIndicator(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .width(100.dp)
                    .height(100.dp),
                color = Color.Red,
                strokeWidth = 2.dp
            )
        }
    }
}