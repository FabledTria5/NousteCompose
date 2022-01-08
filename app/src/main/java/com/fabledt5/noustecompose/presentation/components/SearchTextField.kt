package com.fabledt5.noustecompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.presentation.theme.searchTextBackgroundColor
import com.fabledt5.noustecompose.presentation.theme.searchTextFieldBorderColor
import com.fabledt5.noustecompose.presentation.theme.textFieldCursorColor
import com.fabledt5.noustecompose.presentation.theme.textFieldTextColor

@Composable
fun SearchTextField(text: String, onSearchQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = { onSearchQueryChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = stringResource(R.string.search), color = Color.Gray)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.icon_search),
                tint = Color.Gray
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            backgroundColor = MaterialTheme.colors.searchTextBackgroundColor,
            cursorColor = textFieldCursorColor,
            focusedBorderColor = MaterialTheme.colors.searchTextFieldBorderColor,
            unfocusedBorderColor = MaterialTheme.colors.searchTextFieldBorderColor,
            disabledBorderColor = MaterialTheme.colors.searchTextFieldBorderColor
        ),
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.textFieldTextColor),
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(text = "1234", onSearchQueryChange = {})
}