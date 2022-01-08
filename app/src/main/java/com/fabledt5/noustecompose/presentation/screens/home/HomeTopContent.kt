package com.fabledt5.noustecompose.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.presentation.theme.buttonsColor
import com.fabledt5.noustecompose.presentation.theme.iconsTint
import com.fabledt5.noustecompose.presentation.theme.titlesTextColor

@Composable
fun HomeScreenInfo(
    todayDate: String,
    isListReversed: Boolean,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    onReverseListClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        Text(text = todayDate, color = Color.Gray, fontWeight = FontWeight.Medium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.notes),
                    color = MaterialTheme.colors.titlesTextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.icon_reverse_list),
                    modifier = Modifier
                        .padding(start = 5.dp, top = 10.dp)
                        .size(30.dp)
                        .clickable { onReverseListClicked() }
                        .rotate(degrees = if (!isListReversed) 0f else 180f),
                    tint = MaterialTheme.colors.iconsTint,
                )
            }
            IconButton(
                onClick = { navigateToNoteScreen(-1) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.buttonsColor)
                    .size(45.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.icon_add),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenInfoPreview() {
    HomeScreenInfo(
        todayDate = "FRIDAY 5, APRIL",
        navigateToNoteScreen = {},
        onReverseListClicked = {},
        isListReversed = false
    )
}