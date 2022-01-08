package com.fabledt5.noustecompose.presentation.navigation

import androidx.navigation.NavHostController
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.utils.Constants.HOME_SCREEN
import com.fabledt5.noustecompose.presentation.utils.Constants.NOTE_SCREEN
import com.fabledt5.noustecompose.presentation.utils.Constants.SPLASH_SCREEN

class Screens(navHostController: NavHostController) {

    val splash: () -> Unit = {
        navHostController.navigate(route = "home/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val home: (Action) -> Unit = { action ->
        navHostController.navigate(route = "home/${action.name}") {
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }

    val note: (Int) -> Unit = { noteId ->
        navHostController.navigate(route = "note/$noteId")
    }

}