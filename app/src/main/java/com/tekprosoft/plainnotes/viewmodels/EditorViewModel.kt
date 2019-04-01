package com.tekprosoft.plainnotes.viewmodels

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tekprosoft.plainnotes.database.AppRepository
import com.tekprosoft.plainnotes.database.NoteEntity
import java.util.*
import java.util.concurrent.Executors

class EditorViewModel(application: Application) : AndroidViewModel(application) {

    val mLiveNote = MutableLiveData<NoteEntity>()
    private val mRepository : AppRepository = AppRepository.getInstance(getApplication())
    private val mExecutor = Executors.newSingleThreadExecutor()

    fun loadData(noteId: Int) {
        mExecutor.execute{
            val note = mRepository.getNoteById(noteId)
            mLiveNote.postValue(note)
        }
    }

    fun saveNote(noteText: String) {
        var note = mLiveNote.value
        if (note == null){
            //new note
            if (TextUtils.isEmpty(noteText.trim()))
                return
            note = NoteEntity(Date(),noteText)
        }else{
            //update the existing note
            note.text = noteText
        }
        note?.let { mRepository.insertNote(it) }
    }

    fun deleteNote() {
        mRepository.deleteNote(mLiveNote.value)
    }

}