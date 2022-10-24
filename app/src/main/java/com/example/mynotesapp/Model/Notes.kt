package com.example.mynotesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes" ) //creates a table called 'Notes' with the below columns
class Notes(
    @PrimaryKey(autoGenerate = true) //assigns id as the primary key to the table which is autogenerated
    var id:Int?=null,
    var title:String,
    var subtitle:String,
    var content:String,
    var date:String,
    var color:String
) : Parcelable