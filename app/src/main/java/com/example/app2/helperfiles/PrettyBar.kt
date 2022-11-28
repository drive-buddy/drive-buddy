package com.example.app2.helperfiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
){

    val focusManager = LocalFocusManager.current
    Column() {
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
                .padding(horizontal = 12.dp, vertical = 15.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF888686),
                    unfocusedBorderColor = Color(0xFF888686)
                ),
                trailingIcon = trailingI,
                visualTransformation = keyboardTransformation,
                keyboardOptions = KeyboardSettings,
                keyboardActions = KeyboardActions(
//                onNext = {focusManager.moveFocus(FocusDirection.Down)},
                    onDone = { focusManager.clearFocus() }
                ),
                isError = showError
            )
            if (activeVariable == ""){
                Text(
                    text = "",
                    color = Color(0xFF888686),
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 12.dp)
                )
            }
        }
        if(showError && errorMessage.isNotBlank()) {
            Spacer(Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(0.9f)
            )
        }
    }
}