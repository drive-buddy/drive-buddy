package com.example.app2.helperfiles

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.app2.R

@Composable
fun AddPhoto(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    if (selectedImage != null)
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberImagePainter(selectedImage),
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClick()
                        },
                )
            }

            Spacer(Modifier.height(10.dp))

            Row() {
                Button(
                    onClick = onImageClick,
                    modifier = Modifier
                        .size(16.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(
                            0xFFEE5252
                        )
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        modifier = Modifier.size(80.dp),
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Add photo",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF888686),
                    modifier = Modifier
                        .offset(x = 10.dp)
                )
            }
        }
    else
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.im2),
                contentDescription = "Person",
                modifier = Modifier
                    .size(130.dp)
            )
            Row(

            ) {
                Button(
                    onClick = onImageClick,
                    modifier = Modifier
                        .size(16.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(
                            0xFFEE5252
                        )
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        modifier = Modifier.size(80.dp),
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Add photo",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF888686),
                    modifier = Modifier
                        .offset(x = 10.dp)
                )
            }
        }
}

@Composable
fun Content() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent())
    { uri ->
        selectedImage = uri
    }

    AddPhoto(selectedImage) {
        launcher.launch("image/*")
    }
}
