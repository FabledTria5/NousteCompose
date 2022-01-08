package com.fabledt5.noustecompose.domain.use_case.note_actions

import javax.inject.Inject

data class NotesActionsUseCasesWrapper @Inject constructor(
    val getSelectedNote: GetSelectedNote,
    val saveNote: SaveNote,
    val deleteNote: DeleteNote
)