package com.example.app2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.bgRed

data class HeaderItem(
    val headerId: String,
    val headerTitle: String,
    val headerIcon: ImageVector
)

data class MenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector
    )

@Composable
fun DrawerHeader(
    items: List<HeaderItem>,
    modifier: Modifier = Modifier,
    onItemClick: (HeaderItem) -> Unit
    ){
        LazyColumn(modifier){
            items(items){ item ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(bgRed)
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(15.dp)
                )
                {
                    Row() {
                        Icon(
                            modifier = Modifier
                                .size(35.dp),
                            imageVector = item.headerIcon,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = item.headerTitle,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        )
                    }
                }
            }
        }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
){
    LazyColumn(modifier){
        items(items){ item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(bgRed)
                .clickable {
                    onItemClick(item)
                }
                .padding(16.dp)
                )
            {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
){
    TopAppBar(
        title = {},
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        },
            actions = {
            IconButton(onClick = {/* */}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search drawer",
                    tint = Color.White
                )
            }
            }
    )
}