package com.tekprosoft.plainnotes.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var date : Date = Date()
    var text : String = " "

    //used to edit a note
    constructor(id: Int, date: Date, text: String) {
        this.id = id
        this.date = date
        this.text = text
    }

    //used to add a new note
    @Ignore
    constructor(date: Date, text: String) {
        this.date = date
        this.text = text
    }

    @Ignore
    constructor()

    override fun toString(): String {
        return "NoteEntity(id=$id, date=$date, text='$text')"
    }


}