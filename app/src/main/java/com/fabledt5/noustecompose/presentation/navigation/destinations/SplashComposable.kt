package com.fabledt5.noustecompose.presentation.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.fabledt5.noustecompose.presentation.screens.splash.SplashScreen
import com.fabledt5.noustecompose.presentation.utils.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToHomeScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SplashScreen(navigateToHomeScreen = navigateToHomeScreen)
    }
}