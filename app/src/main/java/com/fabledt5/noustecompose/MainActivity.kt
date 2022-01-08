package com.fabledt5.noustecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.fabledt5.noustecompose.presentation.navigation.SetupNavigation
import com.fabledt5.noustecompose.presentation.theme.NousteComposeTheme
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedViewModel by viewModels<SharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            ProvideWindowInsets {
                NousteComposeTheme {
                    val navController = rememberAnimatedNavController()
                    val systemUiController = rememberSystemUiController()

                    val useDarkIcons = !isSystemInDarkTheme()

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color.Transparent,
                            darkIcons = useDarkIcons
                        )
                        systemUiController.setNavigationBarColor(
                            color = Color.Transparent,
                            darkIcons = useDarkIcons
                        )
                    }
                    SetupNavigation(
                        navHostController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        }
    }
}