package com.fabledt5.noustecompose.domain.use_case.note_actions

import com.fabledt5.noustecompose.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) = notesRepository.deleteNote(noteId = noteId)
}