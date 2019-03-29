package com.tekprosoft.plainnotes

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tekprosoft.plainnotes.database.NoteEntity
import com.tekprosoft.plainnotes.utilities.MyViewBinder.Companion.bind
import com.tekprosoft.plainnotes.viewmodels.EditorViewModel
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {

    private lateinit var mViewModel: EditorViewModel
    private val mNoteText : EditText by bind(R.id.note_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViewModel()
    }

    private fun initViewModel() {
        val noteObserver = Observer<NoteEntity>{
            mNoteText.setText(it.text)
        }

        mViewModel = ViewModelProviders.of(this@EditorActivity)
            .get(EditorViewModel::class.java)

        mViewModel.mLiveNote.observe(this@EditorActivity,noteObserver)
    }
}
