package com.tekprosoft.plainnotes.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.tekprosoft.plainnotes.utilities.SampleData
import java.util.concurrent.Executors

class AppRepository {
    var mNotes : LiveData<List<NoteEntity>>
    private val mDb : AppDatabase
    private val executor  = Executors.newSingleThreadExecutor()

    fun addSampleData() {
        executor.execute{
            mDb.noteDao().insertAll(SampleData().getNotes())
        }
    }

    private constructor(context : Context){
        //mNotes = SampleData().getNotes()
        mDb = AppDatabase.getDatabase(context)
        mNotes = getAllNotes()
    }

    private fun getAllNotes() : LiveData<List<NoteEntity>> {
        return mDb.noteDao().getAll()
    }

    fun deleteAllNotes() {
        executor.execute{
            mDb.noteDao().deleteAllNotes()
        }
    }

    fun getNoteById(noteId: Int): NoteEntity {
        return mDb.noteDao().getNoteById(noteId)
    }

    fun insertNote(note : NoteEntity) {
        executor.execute {
            mDb.noteDao().addUpdateNote(note)
        }
    }

    fun deleteNote(noteEntity: NoteEntity?) {
        executor.execute {
            if (noteEntity != null) {
                mDb.noteDao().deleteNote(noteEntity)
            }
        }
    }

    companion object {
        private lateinit var sInstance : AppRepository

        @JvmStatic
        fun getInstance(context: Context) : AppRepository{
            if (!::sInstance.isInitialized){
                sInstance = AppRepository(context)
            }
            return sInstance
        }

    }
}