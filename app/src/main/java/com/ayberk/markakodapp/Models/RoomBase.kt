package com.ayberk.markakodapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomBase(

    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "image")
    val image:String,
    @ColumnInfo(name = "name")
    val names: String,
    @ColumnInfo(name = "price")
    val prices : String

)