package com.fabledt5.noustecompose.presentation.screens.note

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.noustecompose.R
import com.fabledt5.noustecompose.domain.model.TaskItem
import com.fabledt5.noustecompose.presentation.components.CircleCheckBox
import com.fabledt5.noustecompose.presentation.theme.crimson
import com.fabledt5.noustecompose.presentation.theme.textFieldCursorColor
import com.fabledt5.noustecompose.presentation.theme.textFieldTextColor

@Composable
fun TasksEditContent(
    noteTasks: List<TaskItem>,
    onTaskTextChanged: (newText: String, taskIndex: Int) -> Unit,
    onTaskStatusChanged: (taskIndex: Int) -> Unit,
    onAddTaskClicked: () -> Unit,
    onRemoveTaskClicked: (taskIndex: Int) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        floatingActionButton = { FabAddTask(onAddTaskClicked = onAddTaskClicked) }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = noteTasks) { index, task ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircleCheckBox(
                        isChecked = task.isTaskDone,
                        modifier = Modifier.size(24.dp),
                        color = crimson,
                        onChecked = { onTaskStatusChanged(index) }
                    )
                    OutlinedTextField(
                        value = task.taskText,
                        onValueChange = { onTaskTextChanged(it, index) },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.write_something),
                                color = Color.LightGray
                            )
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.textFieldTextColor,
                            fontSize = 17.sp,
                            textDecoration = if (task.isTaskDone)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.Black,
                            disabledBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            cursorColor = textFieldCursorColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FabAddTask(onAddTaskClicked: () -> Unit) {
    FloatingActionButton(
        onClick = { onAddTaskClicked() },
        backgroundColor = crimson
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.icon_add),
            tint = Color.White
        )
    }
}