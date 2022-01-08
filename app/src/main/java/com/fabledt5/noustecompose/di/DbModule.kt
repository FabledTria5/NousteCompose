package com.fabledt5.noustecompose.di

import android.content.Context
import androidx.room.Room
import com.fabledt5.noustecompose.data.db.NotesDatabase
import com.fabledt5.noustecompose.data.db.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase = Room
        .databaseBuilder(context, NotesDatabase::class.java, "notes_database")
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao = notesDatabase.notesDao()

}