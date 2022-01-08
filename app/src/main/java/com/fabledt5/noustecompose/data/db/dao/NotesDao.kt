package com.fabledt5.noustecompose.data.db.dao

import androidx.room.*
import com.fabledt5.noustecompose.data.db.entity.NoteEntity
import com.fabledt5.noustecompose.data.db.entity.NoteWithTasks
import com.fabledt5.noustecompose.data.db.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(taskEntities: List<TaskEntity>)

    @Query(value = "DELETE FROM notes_table WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Query(value = "SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteWithTasks>>

    @Query(value = "SELECT * FROM notes_table WHERE noteTitle LIKE :searchQuery")
    fun searchNotes(searchQuery: String): Flow<List<NoteWithTasks>>

    @Query(value = "SELECT * FROM notes_table WHERE id = :noteId")
    fun getNoteById(noteId: Int): Flow<NoteWithTasks>

}