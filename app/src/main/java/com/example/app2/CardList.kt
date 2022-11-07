package com.example.app2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

data class Infos(
   val price: String,
   val date: String,
   val time: String,
   val from: String,
   val to: String,
   val name: String,
   val surname: String,
   val carModel: String,
   val license: String,
   val nrOfSeats: String
)

@Composable
fun InfoList(infoss: List<Infos>) {
    LazyColumn(
    ) {
        items(infoss) { info ->
            InfoRow(info)
        }
    }
}

val infoss = listOf(
    Infos("price", "date", "time", "From", "To", "Name", "Surname", "Car model", "License", "Nr")
)

@Composable
fun InfoRow(info: Infos) {
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
                        info.price,
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
                            info.nrOfSeats,
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
                    Text(
                        info.date,
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
                        info.time,
                        modifier = Modifier.padding(3.dp),
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                    )
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
                            info.from,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            info.to,
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
                            info.name,
                            modifier = Modifier.padding(horizontal = 3.dp),
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W700
                        )
                        Text(
                            info.surname,
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W700,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            info.carModel,
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
                            info.license,
                            modifier = Modifier.padding(3.dp),
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W700,
                        )
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
                    onClick = {/**/},
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp),
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