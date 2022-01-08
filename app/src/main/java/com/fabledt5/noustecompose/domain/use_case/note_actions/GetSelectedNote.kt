package com.fabledt5.noustecompose.domain.use_case.note_actions

import com.fabledt5.noustecompose.domain.repository.NotesRepository
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

class GetSelectedNote @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) =
        notesRepository.getNoteById(noteId = noteId).catch { exception ->
            if (exception is NullPointerException) {
                Timber.e(exception)
                emit(null)
            }
        }
}