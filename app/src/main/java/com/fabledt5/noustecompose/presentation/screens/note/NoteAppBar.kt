package com.fabledt5.noustecompose.presentation.screens.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.presentation.theme.appBarContentColor
import com.fabledt5.noustecompose.presentation.theme.crimson
import com.fabledt5.noustecompose.presentation.utils.Action

@Composable
fun NoteAppBar(noteTitle: String, navigateToHomeScreen: (Action) -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackNavigationIcon(onBackClicked = navigateToHomeScreen)
            AppBarTitle(title = noteTitle)
            DoneAction(onDoneClicked = navigateToHomeScreen)
        }
    }
}

@Composable
fun BackNavigationIcon(onBackClicked: (Action) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.arrow_back),
            tint = MaterialTheme.colors.appBarContentColor,
        )
        Text(
            text = stringResource(R.string.back),
            color = MaterialTheme.colors.appBarContentColor,
            fontSize = 17.sp,
        )
    }
}

@Composable
fun AppBarTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(fraction = .6f),
        color = MaterialTheme.colors.appBarContentColor,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

@Composable
fun DoneAction(onDoneClicked: (Action) -> Unit) {
    Text(
        text = stringResource(R.string.done),
        modifier = Modifier.clickable { onDoneClicked(Action.ADD) },
        color = crimson,
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium
    )
}