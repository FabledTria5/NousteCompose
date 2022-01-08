package com.fabledt5.noustecompose.data.repository

import com.fabledt5.noustecompose.data.db.dao.NotesDao
import com.fabledt5.noustecompose.data.db.mapper.toDomain
import com.fabledt5.noustecompose.data.db.mapper.toEntity
import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.repository.NotesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val notesDao: NotesDao) : NotesRepository {

    override fun getAllNotes(): Flow<List<NoteItem>> = notesDao.getAllNotes().toDomain()

    override suspend fun getNoteById(noteId: Int) = notesDao.getNoteById(noteId = noteId).toDomain()

    override suspend fun searchNote(searchQuery: String) =
        notesDao.searchNotes(searchQuery = searchQuery).toDomain()

    override suspend fun saveNote(noteItem: NoteItem) {
        val parentId = notesDao.insertNote(noteEntity = noteItem.toEntity())
        notesDao.insertTasks(taskEntities = noteItem.noteTasks.toEntity(parentId = parentId))
    }

    override suspend fun deleteNote(noteId: Int) = notesDao.deleteNote(noteId = noteId)

}
