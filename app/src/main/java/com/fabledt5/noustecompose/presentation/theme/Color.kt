package com.fabledt5.noustecompose.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val lightGray = Color(0xFFd5d8dd)
val textFieldCursorColor = Color(0xFF007AFF)
val crimson = Color(0xFFFF2424)

val Colors.titlesTextColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.iconsTint: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.buttonsColor: Color
    @Composable
    get() = if (isLight) Color.Black else Purple200

val Colors.selectedGradientColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.searchTextFieldBorderColor: Color
    @Composable
    get() = if (isLight) Color.Transparent else Color.White

val Colors.searchTextBackgroundColor: Color
    @Composable
    get() = if (isLight) lightGray else Color.Transparent

val Colors.textFieldTextColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.appBarContentColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White