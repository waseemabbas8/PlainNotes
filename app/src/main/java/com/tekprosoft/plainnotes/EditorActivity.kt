package com.tekprosoft.plainnotes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tekprosoft.plainnotes.database.NoteEntity
import com.tekprosoft.plainnotes.utilities.AppConstants.Companion.NOTE_ID_KEY
import com.tekprosoft.plainnotes.utilities.MyViewBinder.Companion.bind
import com.tekprosoft.plainnotes.viewmodels.EditorViewModel
import kotlinx.android.synthetic.main.activity_editor.*
import java.util.zip.Inflater

class EditorActivity : AppCompatActivity() {

    private lateinit var mViewModel: EditorViewModel
    private val mNoteText : EditText by bind(R.id.note_text)

    private var newNote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_check)

        initViewModel()
    }

    private fun initViewModel() {
        val noteObserver = Observer<NoteEntity>{
            mNoteText.setText(it.text)
        }

        mViewModel = ViewModelProviders.of(this@EditorActivity)
            .get(EditorViewModel::class.java)

        mViewModel.mLiveNote.observe(this@EditorActivity,noteObserver)

        val extras = intent.extras
        if (extras == null){
            title = getString(R.string.title_new_note)
            newNote = true
        }else{
            title = getString(R.string.title_edit_note)
            val noteId = extras.getInt(NOTE_ID_KEY)
            mViewModel.loadData(noteId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (!newNote){
            menuInflater.inflate(R.menu.menu_editor, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){

            android.R.id.home -> {
                saveAndReturn()
                true
            }
            R.id.action_delete ->{
                deleteAndReturn()
                true
            }else -> {
                false
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAndReturn() {
        mViewModel.deleteNote()
    }

    override fun onBackPressed() {
        saveAndReturn()
        super.onBackPressed()
    }

    private fun saveAndReturn() {
        mViewModel.saveNote(mNoteText.text.toString())
        finish()
    }
}
