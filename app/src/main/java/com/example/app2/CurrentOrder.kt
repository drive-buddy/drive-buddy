package com.example.app2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

//                    val tmp = viewModel.data.value.user

                    Log.i("myUsers", viewModel.data.value.user!!.toString())
                    if (viewModel.data.value.data != null
                        && viewModel.data.value.user!!.isNotEmpty()) {
                        CardOrder(viewModel.data.value.data!!, viewModel.data.value.user!!)

                    } else if (viewModel.data.value.data != null
                        && viewModel.data.value.user!!.isEmpty()) {
                        CardOrder(viewModel.data.value.data!!, listOf(User()))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    androidx.compose.material.Button(
                        onClick = {
                            val navigate = Intent(localContext, AvailableRides::class.java)
                            localContext?.startActivity(navigate)
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(140.dp)
                            .padding(0.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFEE5252)
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
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,

                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                        Text(
                            "Date/time:\n${info.date?.toDate()}",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            Column {
                                Canvas(modifier = Modifier.size(10.dp), onDraw = {
                                    drawCircle(color = Color(0xFFEE5252))
                                })
                                Spacer(modifier = Modifier.height(15.dp))
                                androidx.compose.material.Divider(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxHeight(0.025f)
                                        .width(2.dp)
                                        .offset(4.dp, 0.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Canvas(modifier = Modifier.size(10.dp), onDraw = {
                                    drawCircle(color = Color(0xFFEE5252))
                                })
                            }

                            Column {
                                Text(
                                    text = "${info.from}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .offset(10.dp, 0.dp)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${info.to}",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .offset(10.dp, 0.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(55.dp))

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
                                text = "${user1.userFirstName} ${user1.userLastName}",
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "Car model | Nr",
                                fontSize = 15.sp,
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
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
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
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
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
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
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
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
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
                    Image(
                        painter = painterResource(R.drawable.smoking_no_round), // smoking_no_round | smoking_round
                        contentDescription = "Smoking / No Smoking",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color.White)
                            .clip(CircleShape)
                            .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                    )

                    Image(
                        painter = painterResource(R.drawable.pet_no_round), // pet_no_round | pet_round
                        contentDescription = "Pet | No Pet",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color.White)
                            .clip(CircleShape)
                            .border(3.dp, color = Color(0xFFEE5252), CircleShape)
                    )

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

    private suspend fun findRide(rideID : String) = FirebaseFirestore.getInstance()
        .collection("passengerRequests")
        .document(rideID)

    private suspend fun findRide2(rideID : String) = FirebaseFirestore.getInstance()
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
            if (it["activeOrderID"] != null)
                getRides(it["type"] as String, it["activeOrderID"] as String)
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

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    App2_2Theme {
//        CurrentOrderContent()
//    }
//}


