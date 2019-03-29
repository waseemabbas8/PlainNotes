package com.tekprosoft.plainnotes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tekprosoft.plainnotes.database.AppRepository
import com.tekprosoft.plainnotes.database.NoteEntity

class EditorViewModel(application: Application) : AndroidViewModel(application) {
    val mLiveNote = MutableLiveData<NoteEntity>()
    private val mRepository : AppRepository = AppRepository.getInstance(getApplication())


}