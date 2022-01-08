package com.fabledt5.noustecompose.presentation.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fabledt5.noustecompose.presentation.screens.note.NoteScreen
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.utils.Constants.NOTE_ARGUMENT_KEY
import com.fabledt5.noustecompose.presentation.utils.Constants.NOTE_SCREEN
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalAnimationApi
fun NavGraphBuilder.noteComposable(
    sharedViewModel: SharedViewModel,
    navigateToHomeScreen: (Action) -> Unit
) {
    composable(
        route = NOTE_SCREEN,
        arguments = listOf(navArgument(NOTE_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(durationMillis = 200),
                initialOffsetX = { it }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(durationMillis = 200),
                targetOffsetX = { it }
            )
        }
    ) { navBackStackEntry ->
        val noteId = navBackStackEntry.arguments!!.getInt(NOTE_ARGUMENT_KEY)
        LaunchedEffect(key1 = noteId) { sharedViewModel.getSelectedNote(noteId = noteId) }
        val selectedNote by sharedViewModel.selectedNote.collectAsState()

        LaunchedEffect(key1 = selectedNote) {
            sharedViewModel.updateNoteFields(selectedNote = selectedNote)
        }

        NoteScreen(
            navigateToHomeScreen = navigateToHomeScreen,
            sharedViewModel = sharedViewModel
        )
    }
}