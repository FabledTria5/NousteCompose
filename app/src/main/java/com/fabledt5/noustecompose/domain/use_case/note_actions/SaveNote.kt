package com.fabledt5.noustecompose.domain.use_case.note_actions

import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.repository.NotesRepository
import javax.inject.Inject

class SaveNote @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteItem: NoteItem) = notesRepository.saveNote(noteItem = noteItem)
}