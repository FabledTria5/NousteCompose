package com.fabledt5.noustecompose.presentation.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.navArgument
import com.fabledt5.noustecompose.presentation.screens.home.HomeScreen
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.utils.Constants.HOME_ARGUMENT_KEY
import com.fabledt5.noustecompose.presentation.utils.Constants.HOME_SCREEN
import com.fabledt5.noustecompose.presentation.utils.toAction
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.navigation.animation.composable

@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable(
    navigateToNoteScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = HOME_SCREEN,
        arguments = listOf(navArgument(HOME_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments!!.getString(HOME_ARGUMENT_KEY).toAction()

        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if (action != myAction) {
                myAction = action
                sharedViewModel.action.value = action
            }
        }

        val notesAction by sharedViewModel.action

        HomeScreen(
            action = notesAction,
            navigateToNoteScreen = navigateToNoteScreen,
            sharedViewModel = sharedViewModel
        )
    }
}