package com.ayberk.markakodapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ayberk.markakodapp.Models.RoomBase

@Dao
interface DataDao {

    @Query("SELECT * FROM RoomBase")
    fun getAll(): List<RoomBase>
    @Delete
    fun delete(advent: RoomBase)
    @Insert
    fun insert(advent: RoomBase)

    @Query("SELECT COUNT(*) FROM RoomBase WHERE id= :id")
    fun checkIfDataExists(id: Int): Int

}