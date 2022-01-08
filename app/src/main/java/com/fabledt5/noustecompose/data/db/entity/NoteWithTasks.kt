package com.fabledt5.noustecompose.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class NoteWithTasks(
    @Embedded val noteEntity: NoteEntity,
    @Relation(parentColumn = "id", entityColumn = "parentId")
    val taskEntities: List<TaskEntity>
)