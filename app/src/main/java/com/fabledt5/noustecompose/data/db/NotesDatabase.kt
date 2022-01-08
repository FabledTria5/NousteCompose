package com.fabledt5.noustecompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabledt5.noustecompose.data.db.dao.NotesDao
import com.fabledt5.noustecompose.data.db.entity.NoteEntity
import com.fabledt5.noustecompose.data.db.entity.TaskEntity

@Database(entities = [NoteEntity::class, TaskEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}