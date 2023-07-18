package com.ayberk.markakodapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayberk.markakodapp.Models.RoomBase

@Database(entities = [RoomBase :: class], version = 1)
abstract class RoomDatabase : RoomDatabase(){

    abstract fun dataDao() : DataDao
}