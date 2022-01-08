package com.fabledt5.noustecompose.data.db.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fabledt5.noustecompose.presentation.utils.Gradient

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val noteTitle: String,
    val noteText: String,
    val noteImage: Bitmap?,
    val noteGradient: Gradient
)