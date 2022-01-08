package com.fabledt5.noustecompose.data.db.mapper

import com.fabledt5.noustecompose.data.db.entity.NoteEntity
import com.fabledt5.noustecompose.data.db.entity.TaskEntity
import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.model.TaskItem

fun NoteItem.toEntity() = NoteEntity(
    id = noteId.toLong(),
    noteTitle = noteTitle,
    noteText = noteText,
    noteGradient = noteGradient,
    noteImage = noteImage
)

fun List<TaskItem>.toEntity(parentId: Long) = map {
    TaskEntity(parentId = parentId, taskText = it.taskText, isTaskDone = it.isTaskDone)
}