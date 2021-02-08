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
    suspend fun getAllWork(carId: Int): List<Work>


    @Insert
    suspend fun insertWork(work: Work)

    @Update
    suspend fun updateWork(work: Work)

    @Delete
    suspend fun deleteWork(work: Work)


}