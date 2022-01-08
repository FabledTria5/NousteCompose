package com.fabledt5.noustecompose.domain.model

import android.graphics.Bitmap
import com.fabledt5.noustecompose.presentation.utils.Gradient

data class NoteItem(
    val noteId: Int,
    val noteTitle: String = "",
    val noteText: String = "",
    val noteImage: Bitmap? = null,
    val noteGradient: Gradient = Gradient.Default,
    val noteTasks: List<TaskItem> = listOf()
)