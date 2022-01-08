package com.fabledt5.noustecompose.domain.repository

import com.fabledt5.noustecompose.domain.model.NoteItem
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getAllNotes(): Flow<List<NoteItem>>

    suspend fun getNoteById(noteId: Int): Flow<NoteItem?>

    suspend fun searchNote(searchQuery: String): Flow<List<NoteItem>>

    suspend fun saveNote(noteItem: NoteItem)

    suspend fun deleteNote(noteId: Int)

}