package com.demo.homework5.work

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.demo.homework5.Car

@Dao
interface WorkDao {

    @Query("SELECT * FROM Work where carId = :carId ORDER BY cost")
    fun getAllWork(carId:Int): List<Work>

    @Query("SELECT * FROM Work where carId = :carId ORDER BY cost")
    fun getAllWorkRX(carId:Int): List<Work>

//    @Query("SELECT * FROM Work where carId = :carId ORDER BY progressItem")
//    fun getAllWorkPending(carId:Int): List<Work>

    @Insert
    fun insertWork(work: Work)

    @Update
    fun updateWork(work: Work)

    @Delete
    fun deleteWork(work: Work)




}