package com.tekprosoft.plainnotes

import android.util.Log
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.tekprosoft.plainnotes.database.AppDatabase
import com.tekprosoft.plainnotes.database.NoteDao
import com.tekprosoft.plainnotes.utilities.SampleData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    val TAG = "Jnuit"
    private lateinit var mDb : AppDatabase
    private lateinit var mDao : NoteDao

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        mDao = mDb.noteDao()

        Log.i(TAG,"Create Db")
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        if (::mDb.isInitialized)
            mDb.close()
        Log.i(TAG,"Close Db")
    }

    @Test
    @Throws(Exception::class)
    fun createAndRetrieveNotes(){
        val notes = SampleData().getNotes()
        mDao.insertAll(notes)
        val count = mDao.getCount()
        Log.i(TAG, "Notes Saved = $count")
        assertEquals(notes.size,count)
    }

}