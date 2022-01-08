package com.fabledt5.noustecompose.presentation.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabledt5.noustecompose.domain.model.AppState
import com.fabledt5.noustecompose.domain.model.ListsOrder
import com.fabledt5.noustecompose.domain.model.NoteItem
import com.fabledt5.noustecompose.domain.model.TaskItem
import com.fabledt5.noustecompose.domain.use_case.GetNotes
import com.fabledt5.noustecompose.domain.use_case.note_actions.NotesActionsUseCasesWrapper
import com.fabledt5.noustecompose.domain.use_case.preferences.PreferencesActionsWrapper
import com.fabledt5.noustecompose.presentation.utils.Action
import com.fabledt5.noustecompose.presentation.utils.Constants.DATE_PATTERN
import com.fabledt5.noustecompose.presentation.utils.Gradient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getNotes: GetNotes,
    private val preferencesActionsWrapper: PreferencesActionsWrapper,
    private val notesActionsUseCasesWrapper: NotesActionsUseCasesWrapper
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val searchQuery = mutableStateOf(value = "")

    val noteId = mutableStateOf(value = 0)
    val noteTitle = mutableStateOf(value = "")
    val noteText = mutableStateOf(value = "")
    val noteGradient = mutableStateOf(value = Gradient.Default)
    val noteImage: MutableState<Bitmap?> = mutableStateOf(value = null)
    val noteTasks = mutableStateListOf<TaskItem>()

    private val _currentDate = MutableStateFlow(value = "")
    val currentDate = _currentDate.asStateFlow()

    private val _notesListOrder = MutableStateFlow(value = ListsOrder.NORMAL)
    val notesListOrder = _notesListOrder.asStateFlow()

    private val _allNotes = MutableStateFlow<AppState<List<NoteItem>>>(AppState.Idle)
    val allNotes = _allNotes.asStateFlow()

    private val _searchedNotes = MutableStateFlow<AppState<List<NoteItem>>>(AppState.Idle)
    val searchedNotes = _searchedNotes.asStateFlow()

    private val _selectedNote = MutableStateFlow<NoteItem?>(value = null)
    val selectedNote = _selectedNote.asStateFlow()

    init {
        getCurrentDate()
        getListOrder()
    }

    fun getSelectedNote(noteId: Int) = viewModelScope.launch(Dispatchers.IO) {
        notesActionsUseCasesWrapper.getSelectedNote(noteId = noteId).collect { note ->
            _selectedNote.value = note
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun toggleListOrder() = viewModelScope.launch(Dispatchers.IO) {
        preferencesActionsWrapper.setListOrder(order = _notesListOrder.value)
    }

    fun updateNoteFields(selectedNote: NoteItem?) {
        if (selectedNote != null) {
            noteId.value = selectedNote.noteId
            noteTitle.value = selectedNote.noteTitle
            noteText.value = selectedNote.noteText
            noteImage.value = selectedNote.noteImage
            noteGradient.value = selectedNote.noteGradient
            noteTasks.clear()
            noteTasks.addAll(selectedNote.noteTasks)
        } else {
            noteId.value = 0
            noteTitle.value = ""
            noteText.value = ""
            noteImage.value = null
            noteGradient.value = Gradient.Default
            noteTasks.clear()
        }
    }

    fun handleActionChange(action: Action) {
        when (action) {
            Action.ADD -> addNote()
            Action.DELETE -> deleteNote()
            Action.UNDO -> addNote()
            else -> Unit
        }
    }

    fun validateFields() = noteTitle.value.isNotEmpty() && noteText.value.isNotEmpty()

    fun updateTitle(newTitle: String) {
        noteTitle.value = newTitle
    }

    fun updateNoteText(newText: String) {
        noteText.value = newText
    }

    fun updateGradient(newGradient: Gradient) {
        noteGradient.value = newGradient
    }

    fun updateNoteImage(newImage: Bitmap?) {
        noteImage.value = newImage
    }

    fun addTask() = noteTasks.add(TaskItem())

    fun removeTask(taskIndex: Int) = noteTasks.removeAt(taskIndex)

    fun updateTaskStatus(taskIndex: Int) {
        noteTasks[taskIndex] =
            noteTasks[taskIndex].copy(isTaskDone = !noteTasks[taskIndex].isTaskDone)
    }

    fun updateTaskText(newText: String, taskIndex: Int) {
        noteTasks[taskIndex] = noteTasks[taskIndex].copy(taskText = newText)
    }

    private fun getCurrentDate() = viewModelScope.launch(Dispatchers.IO) {
        val calendar = Calendar.getInstance()
        _currentDate.value =
            SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH).format(calendar.time).uppercase()
    }

    private fun getListOrder() = preferencesActionsWrapper.getListOrder().onEach { order ->
        _notesListOrder.value = order
        getAllNotes()
    }.launchIn(viewModelScope)

    private fun getAllNotes() {
        _allNotes.value = AppState.Loading
        getNotes(_notesListOrder.value).onEach { result ->
            _allNotes.value = result
        }.launchIn(viewModelScope)
    }

    private fun addNote() = viewModelScope.launch(Dispatchers.IO) {
        notesActionsUseCasesWrapper.saveNote(
            noteItem = NoteItem(
                noteId = noteId.value,
                noteTitle = noteTitle.value,
                noteText = noteText.value,
                noteImage = noteImage.value,
                noteGradient = noteGradient.value,
                noteTasks = noteTasks
            )
        )
    }

    private fun deleteNote() = viewModelScope.launch(Dispatchers.IO) {
        notesActionsUseCasesWrapper.deleteNote(noteId = noteId.value)
    }

}