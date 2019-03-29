package com.tekprosoft.plainnotes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tekprosoft.plainnotes.database.AppRepository
import com.tekprosoft.plainnotes.database.NoteEntity

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val mNotes : LiveData<List<NoteEntity>>
    private val mRepository : AppRepository

    init {
        mRepository = AppRepository.getInstance(application.applicationContext)
        mNotes = mRepository.mNotes
    }

    fun addSampleData() {
        mRepository.addSampleData()
    }

    fun deleteAllNotes() {
        mRepository.deleteAllNotes()
    }
}