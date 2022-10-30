package com.example.app2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//class ComposeableBar {
//}

@Composable
fun PrettyBar(
    modifier: Modifier = Modifier,
    type: String = "",
    hint: String = "",
    activeVariable: String = "",
    onVarChange: (String) -> Unit = {},
    trailingI : @Composable (() -> Unit)? = null,
    KeyboardSettings : KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next),
    keyboardTransformation : VisualTransformation = VisualTransformation.None,
    showError: Boolean = false,
    errorMessage: String = ""
) {

    val focusManager = LocalFocusManager.current
    Box(modifier = modifier) {
        OutlinedTextField(
            value = activeVariable,
            onValueChange = onVarChange,
            singleLine = true,
            textStyle = TextStyle(color = Color(0xFF888686)),
            placeholder = { Text(text = hint, color = Color(0xFF888686)) },
            label = {
                Text(
                    text = type,
                    color = Color(0xFF888686),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CutCornerShape(10))
                .background(
                    color = Color(0xFFFAF7F7),
                    shape = CutCornerShape(10)
                )
            ,colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF888686),
                unfocusedBorderColor = Color(0xFF888686)
            ),
            trailingIcon = trailingI,
            visualTransformation = keyboardTransformation,
            keyboardOptions = KeyboardSettings,
            keyboardActions = KeyboardActions(
//                onNext = {focusManager.moveFocus(FocusDirection.Down)}
                onDone = { focusManager.clearFocus() }
            ),
            isError = showError
        )
        if (showError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .offset(y = (76).dp)
                    .fillMaxWidth(0.9f)
            )

        }
    }
}