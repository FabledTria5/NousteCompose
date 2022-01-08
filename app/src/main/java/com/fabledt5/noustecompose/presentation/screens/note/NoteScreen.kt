package com.fabledt5.noustecompose.presentation.screens.note

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.domain.model.TaskItem
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.utils.Gradient
import com.fabledt5.noustecompose.presentation.viewmodel.SharedViewModel
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun NoteScreen(
    navigateToHomeScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val noteTitle by sharedViewModel.noteTitle
    val noteText by sharedViewModel.noteText
    val noteGradient by sharedViewModel.noteGradient
    val noteImage by sharedViewModel.noteImage
    val tasksList = sharedViewModel.noteTasks

    val context = LocalContext.current

    BackHandler { navigateToHomeScreen(Action.NO_ACTION) }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            NoteAppBar(
                navigateToHomeScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToHomeScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToHomeScreen(action)
                        } else {
                            displayInvalidFieldsToast(context = context)
                        }
                    }
                },
                noteTitle = noteTitle
            )
        },
        content = {
            NoteContent(
                title = noteTitle,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                noteText = noteText,
                onNoteTextChange = { sharedViewModel.updateNoteText(it) },
                noteGradient = noteGradient,
                onGradientChange = { sharedViewModel.updateGradient(it) },
                noteImage = noteImage,
                onNoteImageChange = { sharedViewModel.updateNoteImage(it) },
                noteTasks = tasksList,
                onAddTaskClicked = { sharedViewModel.addTask() },
                onRemoveTaskClicked = { },
                onTaskStatusChanged = { sharedViewModel.updateTaskStatus(it) },
                onTaskTextChanged = { newText, taskIndex ->
                    sharedViewModel.updateTaskText(newText = newText, taskIndex = taskIndex)
                }
            )
        }
    )
}

@ExperimentalPagerApi
@Composable
fun NoteContent(
    title: String,
    onTitleChange: (String) -> Unit,
    noteText: String,
    onNoteTextChange: (String) -> Unit,
    noteGradient: Gradient,
    onGradientChange: (Gradient) -> Unit,
    noteImage: Bitmap?,
    onNoteImageChange: (Bitmap?) -> Unit,
    noteTasks: List<TaskItem>,
    onTaskTextChanged: (String, Int) -> Unit,
    onTaskStatusChanged: (Int) -> Unit,
    onAddTaskClicked: () -> Unit,
    onRemoveTaskClicked: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()

    val tabsContent = stringArrayResource(id = R.array.note_tabs)

    Column(modifier = Modifier.padding(top = 20.dp)) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
            backgroundColor = Color.White
        ) {
            tabsContent.forEachIndexed { index, tabName ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.background(
                        if (pagerState.currentPage == index)
                            Color.Black
                        else
                            Color.White
                    )
                ) {
                    Text(
                        text = tabName,
                        color = if (pagerState.currentPage == index) Color.White else Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                    )
                }
            }
        }
        HorizontalPager(
            count = tabsContent.size,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> NoteEditContent(
                    title = title,
                    onTitleChange = onTitleChange,
                    noteText = noteText,
                    onNoteTextChange = onNoteTextChange,
                    noteGradient = noteGradient,
                    onGradientChange = onGradientChange,
                    noteImage = noteImage,
                    onNoteImageChange = onNoteImageChange
                )
                1 -> TasksEditContent(
                    noteTasks = noteTasks,
                    onTaskTextChanged = onTaskTextChanged,
                    onTaskStatusChanged = onTaskStatusChanged,
                    onAddTaskClicked = onAddTaskClicked,
                    onRemoveTaskClicked = onRemoveTaskClicked
                )
            }
        }
    }
}

private fun displayInvalidFieldsToast(context: Context) =
    Toast.makeText(context, "Fields Empty", Toast.LENGTH_SHORT).show()