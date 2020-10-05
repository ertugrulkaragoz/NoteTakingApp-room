package com.example.notetaking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note(

    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "note")
    val note : String
):Serializable {
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    var Id : Int = 0
}