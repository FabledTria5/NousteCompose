package com.fabledt5.noustecompose.data.db.mapper

import com.fabledt5.noustecompose.data.db.entity.NoteWithTasks
import com.fabledt5.noustecompose.data.db.entity.TaskEntity
import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.model.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("toDomainNoteWithTasks")
fun Flow<List<NoteWithTasks>>.toDomain(): Flow<List<NoteItem>> = map { notesList ->
    notesList.map { entity ->
        NoteItem(
            noteId = entity.noteEntity.id.toInt(),
            noteTitle = entity.noteEntity.noteTitle,
            noteText = entity.noteEntity.noteText,
            noteImage = entity.noteEntity.noteImage,
            noteGradient = entity.noteEntity.noteGradient,
            noteTasks = entity.taskEntities.toDomain()
        )
    }
}

fun Flow<NoteWithTasks>.toDomain() = map { noteWithTasks ->
    NoteItem(
        noteId = noteWithTasks.noteEntity.id.toInt(),
        noteTitle = noteWithTasks.noteEntity.noteTitle,
        noteText = noteWithTasks.noteEntity.noteText,
        noteImage = noteWithTasks.noteEntity.noteImage,
        noteGradient = noteWithTasks.noteEntity.noteGradient,
        noteTasks = noteWithTasks.taskEntities.toDomain()
    )
}

fun NoteWithTasks.toDomain() = NoteItem(
    noteId = noteEntity.id.toInt(),
    noteTitle = noteEntity.noteTitle,
    noteText = noteEntity.noteText,
    noteImage = noteEntity.noteImage,
    noteGradient = noteEntity.noteGradient,
    noteTasks = taskEntities.toDomain()
)

private fun List<TaskEntity>.toDomain() = map { entity ->
    TaskItem(taskText = entity.taskText, isTaskDone = entity.isTaskDone)
}
