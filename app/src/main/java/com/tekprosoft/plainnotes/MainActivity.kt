package com.tekprosoft.plainnotes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekprosoft.plainnotes.database.NoteEntity
import com.tekprosoft.plainnotes.ui.NotesAdapter
import com.tekprosoft.plainnotes.utilities.SampleData

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private val notesData = ArrayList<NoteEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        notesData.addAll(SampleData().getNotes())
        initRecyclerView()

        fab.setOnClickListener {
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val intent = Intent(this@MainActivity,EditorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView(){
        mRecyclerView = rv_notes
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = NotesAdapter(notesData)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
