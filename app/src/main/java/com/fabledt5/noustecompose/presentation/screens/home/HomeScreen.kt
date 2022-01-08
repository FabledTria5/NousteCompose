package com.fabledt5.noustecompose.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fabledt5.noustecompose.domain.model.ListsOrder
import com.fabledt5.noustecompose.presentation.components.SearchTextField
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    action: Action,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = action) { sharedViewModel.handleActionChange(action) }

    val searchedQuery by sharedViewModel.searchQuery

    val currentDate by sharedViewModel.currentDate.collectAsState()
    val notesOrder by sharedViewModel.notesListOrder.collectAsState()
    val allNotes by sharedViewModel.allNotes.collectAsState()
    val searchedNotes by sharedViewModel.searchedNotes.collectAsState()

    val scaffoldState = rememberScaffoldState()

    ShowSnackBar(
        scaffoldState = scaffoldState,
        action = action,
        onUndoClicked = { sharedViewModel.action.value = it },
        onComplete = { sharedViewModel.action.value = it },
        noteTitle = sharedViewModel.noteTitle.value
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeScreenInfo(
                todayDate = currentDate,
                navigateToNoteScreen = navigateToNoteScreen,
                onReverseListClicked = { sharedViewModel.toggleListOrder() },
                isListReversed = notesOrder == ListsOrder.REVERSED
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchTextField(
                text = searchedQuery,
                onSearchQueryChange = { sharedViewModel.updateSearchQuery(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HomeListContent(
                allNotes = allNotes,
                searchedNotes = searchedNotes,
                navigateToNoteScreen = navigateToNoteScreen,
                searchState = searchedQuery.isNotEmpty(),
                onDeleteNoteClicked = { noteItem ->
                    sharedViewModel.updateNoteFields(noteItem)
                    sharedViewModel.action.value = Action.DELETE
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
            )
        }
    }
}

@Composable
fun ShowSnackBar(
    scaffoldState: ScaffoldState,
    action: Action,
    onUndoClicked: (Action) -> Unit,
    onComplete: (Action) -> Unit,
    noteTitle: String
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "${action.name}: $noteTitle",
                    actionLabel = if (action == Action.DELETE) "UNDO" else "OK"
                )
                undoDeletedNote(
                    action = action,
                    snackbarResult = snackbarResult,
                    onUndoClicked = onUndoClicked
                )
            }
            onComplete(Action.NO_ACTION)
        }
    }
}

fun undoDeletedNote(
    action: Action,
    snackbarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) = if (snackbarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
    onUndoClicked(Action.UNDO)
} else Unit
