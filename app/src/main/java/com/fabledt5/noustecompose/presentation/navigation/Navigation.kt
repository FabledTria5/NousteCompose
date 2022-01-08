package com.fabledt5.noustecompose.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.fabledt5.noustecompose.presentation.navigation.destinations.homeComposable
import com.fabledt5.noustecompose.presentation.navigation.destinations.noteComposable
import com.fabledt5.noustecompose.presentation.navigation.destinations.splashComposable
import com.fabledt5.noustecompose.presentation.utils.Constants.SPLASH_SCREEN
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController = navHostController)
    }

    AnimatedNavHost(navController = navHostController, startDestination = SPLASH_SCREEN) {
        splashComposable(navigateToHomeScreen = screen.splash)
        homeComposable(navigateToNoteScreen = screen.note, sharedViewModel = sharedViewModel)
        noteComposable(sharedViewModel = sharedViewModel, navigateToHomeScreen = screen.home)
    }
}