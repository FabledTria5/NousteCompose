package com.fabledt5.noustecompose.di

import com.fabledt5.noustecompose.data.repository.DataStoreRepositoryImpl
import com.fabledt5.noustecompose.data.repository.NotesRepositoryImpl
import com.fabledt5.noustecompose.domain.repository.DataStoreRepository
import com.fabledt5.noustecompose.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository

    @Binds
    fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

}