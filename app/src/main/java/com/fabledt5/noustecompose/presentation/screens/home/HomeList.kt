package com.fabledt5.noustecompose.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.domain.model.AppState
import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.model.TaskItem
import com.fabledt5.noustecompose.presentation.components.CircleCheckBox
import com.fabledt5.noustecompose.presentation.components.StaggeredVerticalGrid

@ExperimentalMaterialApi
@Composable
fun HomeListContent(
    allNotes: AppState<List<NoteItem>>,
    searchedNotes: AppState<List<NoteItem>>,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    onDeleteNoteClicked: (note: NoteItem) -> Unit,
    searchState: Boolean
) {
    if (searchState && searchedNotes is AppState.Success) {
        HandleListContent(
            notes = searchedNotes.data,
            navigateToNoteScreen = navigateToNoteScreen,
            onDeleteNoteClicked = onDeleteNoteClicked
        )
    } else {
        if (allNotes is AppState.Success) {
            HandleListContent(
                notes = allNotes.data,
                navigateToNoteScreen = navigateToNoteScreen,
                onDeleteNoteClicked = onDeleteNoteClicked
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    notes: List<NoteItem>,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    onDeleteNoteClicked: (note: NoteItem) -> Unit
) {
    if (notes.isNotEmpty()) ShowNotesList(
        notes = notes,
        navigateToNoteScreen = navigateToNoteScreen,
        onDeleteNoteClicked = onDeleteNoteClicked
    ) else EmptyContent()
}

@ExperimentalMaterialApi
@Composable
fun ShowNotesList(
    notes: List<NoteItem>,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    onDeleteNoteClicked: (note: NoteItem) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        StaggeredVerticalGrid(numColumns = 2) {
            notes.forEach { note ->
                NoteItem(
                    noteItem = note,
                    navigateToNoteScreen = navigateToNoteScreen,
                    onDeleteNoteClicked = onDeleteNoteClicked
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NoteItem(
    noteItem: NoteItem,
    navigateToNoteScreen: (noteId: Int) -> Unit,
    onDeleteNoteClicked: (note: NoteItem) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        onClick = { navigateToNoteScreen(noteItem.noteId) }
    ) {
        Column(
            Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(noteItem.noteGradient.startColor.toColorInt()),
                            Color(noteItem.noteGradient.endColor.toColorInt())
                        )
                    )
                )
                .padding(start = 15.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        text = noteItem.noteTitle,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.icon_more),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(15.dp)
                            .clickable { isDropDownExpanded = true },
                        tint = Color.White.copy(alpha = .5f)
                    )
                    DropdownMenu(
                        expanded = isDropDownExpanded,
                        onDismissRequest = { isDropDownExpanded = false }
                    ) {
                        DropdownMenuItem(onClick = { onDeleteNoteClicked(noteItem) }) {
                            Text(text = "Delete note")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            repeat(times = noteItem.noteTasks.size) {
                TaskItem(
                    task = noteItem.noteTasks[it],
                    itemIndex = it,
                    textColor = Color.White,
                    checkBoxColor = Color.White
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskItem, itemIndex: Int, textColor: Color, checkBoxColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .alpha(alpha = (1 - itemIndex.toFloat() / 10) - 0.1f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleCheckBox(
            isChecked = task.isTaskDone,
            modifier = Modifier.size(20.dp),
            onChecked = {},
            color = checkBoxColor,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = task.taskText,
            color = textColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textDecoration = if (task.isTaskDone)
                TextDecoration.LineThrough
            else
                TextDecoration.None,
            fontSize = 14.sp
        )
    }
}