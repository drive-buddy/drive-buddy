package com.example.app2.helperfiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.R
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

data class Infos(
    @DocumentId
    val id : String? = "",
    val from: String? = "",
    val to: String? = "",
    val date: Timestamp? = null,
    val nrOfSeats: Int? = null,
    val price: Int? = null,
    val driver: String? = "",
    val passenger1: String? = "",
    val passenger2: String? = "",
    val passenger3: String? = "",
) : java.io.Serializable

data class User(
    @DocumentId
    val id : String? = "",
    val userFirstName: String? = "",
    val userLastName: String? = "",
    val email: String? = "",
    val carModel: String? = "",
    val carNumber: String? = "",
    val userPhoneNumber: String? = "",
    val yearsOfExp: String? = "",

) : java.io.Serializable
//val infoss = listOf(
//    Infos("price", "date", "time", "From", "To", "Name", "Surname", "Car model", "License", "Nr")
//)

@Composable
fun InfoRow(
    info: Infos,
    user: User,
    type : String = "passenger",
    buttonBehavior: (info : Infos) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(270.dp)
            .width(400.dp)
            .padding(vertical = 10.dp, horizontal = 40.dp),
        shape = RoundedCornerShape(10)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = info.price!!.toString() + " lei",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700
                    )
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.available_seats),
                            contentDescription = "Available seats",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = info.nrOfSeats!!.toString(),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700
                        )
                    }
                }
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(1.dp)
                        .width(40.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val tmp = info.date!!.toDate()
                    Text(
                        text = tmp.toString(),
                        modifier = Modifier.padding(3.dp),
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                    )
//                    Divider(
//                        color = Color.Black,
//                        modifier = Modifier
//                            .height(12.dp)
//                            .width(1.dp)
//                    )
//                    Text(
//                        text = info.time!!,
//                        modifier = Modifier.padding(3.dp),
//                        color = Color.Black,
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.W700,
//                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.points),
                        contentDescription = "Destination",
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            info.from!!,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            info.to!!,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person_gray),
                        contentDescription = "Person",
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Row() {
                        Text(
                            user.userFirstName!!,
                            modifier = Modifier.padding(horizontal = 3.dp),
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W700
                        )
                        Text(
                            user.userLastName!!,
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W700,
                        )
                    }
                    if (info.driver != "") {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = user.carModel!!,
                                modifier = Modifier.padding(3.dp),
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W700,
                            )
                            Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .height(12.dp)
                                    .width(1.dp)
                            )
                            Text(
                                text = user.carNumber!!,
                                modifier = Modifier.padding(3.dp),
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W700,
                            )
                        }
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        )
        {
            Button(
                onClick = { buttonBehavior(info) },
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp)
                    .offset(y = (-10).dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFEE5252))
            ) {
                Text(
                    text = "Book",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}