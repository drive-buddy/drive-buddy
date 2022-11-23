package com.example.app2
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.drawer.DrawerLayout
import com.example.app2.helperfiles.*
import com.example.app2.ui.theme.App2Theme
import com.example.app2.ui.theme.Grey

class No_result : ComponentActivity() {
    var db: DBHelper = DBHelper(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            App2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DrawerLayout(
                        contentFun = {
                            padding ->
                        Box(modifier = Modifier
                            .absolutePadding(
                                left = 35.dp,
                                top = 30.dp)
                        ) {

                            Card(modifier = Modifier
                                .height(30.dp)
                                .width(170.dp),
                                shape = RoundedCornerShape(15.dp),
                                elevation = 5.dp,
                            ) {
                                Text(
                                    text = "Results:",
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.Black
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 40.dp, vertical = 80.dp),
                            shape = RoundedCornerShape(30.dp),
                            elevation = 5.dp
                        ) {

                            Column(modifier = Modifier
                                .padding(horizontal = 40.dp, vertical = 145.dp)
                            ) {

                                Text(
                                    text = "There are no such rides set yet",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    color = Grey
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                )

                            }
                        }
                    },
                    floatingActionButtonFun = {
                        Button(onClick = {
                            val navigate = Intent(this@No_result, Schedule_ride::class.java)
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
                    },
                    );
                }
            }
        }
    }
}