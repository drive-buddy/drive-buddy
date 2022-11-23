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
import kotlinx.coroutines.launch

@Composable
fun DrawerLayout(
    localContext: Context? = null,
    floatingActionButtonFun: @Composable () -> Unit = {},
    contentFun: @Composable (PaddingValues) -> Unit,
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
                }
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
                        id = "help",
                        title = "Help",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "support",
                        title = "Support",
                        icon = Icons.Default.Info
                    ),
                    MenuItem(
                        id = "signout",
                        title = "Sign Out",
                        icon = Icons.Default.Info
                    ),
                ),
                onItemClick = {
                    val dbEntry : DBHelper = DBHelper()

                    when(it.id){
                        "current ride" -> {
                            val navigate = Intent(localContext, CurrentOrder::class.java)
                            localContext?.startActivity(navigate)
                        }
                        "signout" -> dbEntry.signout()
                    }
                    println("Clicked on ${it.title}")
                }
            )
        },
        content = contentFun,
//        contentColor = Color.White,
    )
}