package com.fabledt5.noustecompose.domain.use_case

import com.fabledt5.noustecompose.domain.model.AppState
import com.fabledt5.noustecompose.domain.model.ListsOrder
import com.fabledt5.noustecompose.domain.repository.NotesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(listOrder: ListsOrder) = notesRepository.getAllNotes()
        .map { notesList ->
            if (notesList.isNullOrEmpty()) AppState.Error(Throwable("Empty Array"))
            else {
                when (listOrder) {
                    ListsOrder.NORMAL -> AppState.Success(data = notesList)
                    ListsOrder.REVERSED -> AppState.Success(
                        data = notesList.sortedByDescending { it.noteId }
                    )
                }
            }
        }
        .catch { exception ->
            Timber.e(exception)
            emit(AppState.Error(message = exception))
        }
}