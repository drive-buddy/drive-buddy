package com.example.app2.drawer

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.app2.*
import com.example.app2.helperfiles.DBHelper
import com.example.app2.rides.AvailableRides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DrawerLayout(
    localContext: Context? = null,
    floatingActionButtonFun: @Composable () -> Unit = {},
    contentFun: @Composable (PaddingValues) -> Unit,
    textHeaderField : String = "",
    ){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                textField = textHeaderField,
            )
        },
        floatingActionButton = floatingActionButtonFun,
        drawerBackgroundColor = Color(0xFFEE5252),
        drawerContent = {
            DrawerHeader(
                items = listOf(
                    HeaderItem(
                        headerId = "username",
                        headerTitle = "Username",
                        headerIcon = Icons.Default.AccountCircle
                    )
                ),
                onItemClick = {
//                                    when(it.id){
//                                        "settings" -> navigateToSettingsScreen
//                                    }
                    println("Clicked on ${it.headerTitle}")
                }
            )
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "current ride",
                        title = "Current ride",
                        icon = Icons.Default.LocationOn
                    ),
                    MenuItem(
                        id = "support",
                        title = "Support",
                        icon = Icons.Default.MailOutline
                    ),
                    MenuItem(
                        id = "about",
                        title = "About Us",
                        icon = Icons.Default.Info
                    ),
                    MenuItem(
                        id = "signout",
                        title = "Sign Out",
                        icon = Icons.Default.ExitToApp
                    ),
                ),
                onItemClick = {
                    val dbEntry : DBHelper = DBHelper()

                    when(it.id){
                        "home" -> {
                            val navigate = Intent(localContext, AvailableRides::class.java)
                            localContext?.startActivity(navigate)
                        }
                        "current ride" -> {
                            val navigate = Intent(localContext, CurrentOrder::class.java)
                            localContext?.startActivity(navigate)
                        }
                        "support" -> {
                            val navigate = Intent(localContext, SendEmail::class.java)
                            localContext?.startActivity(navigate)
                        }
                        "about" -> {
                            val navigate = Intent(localContext, AboutApp::class.java)
                            localContext?.startActivity(navigate)
                        }
                        "signout" -> {
                            dbEntry.signout()
                            val navigate = Intent(localContext, StartPage::class.java)
                            localContext?.startActivity(navigate)
                        }
                    }
                    println("Clicked on ${it.title}")
                }
            )
        },
        content = contentFun,
//        contentColor = Color.White,
    )
}