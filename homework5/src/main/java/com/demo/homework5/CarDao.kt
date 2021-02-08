package com.demo.homework5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {

    @Query("SELECT * FROM Car ORDER BY modelCar")
   suspend fun getAllCar(): List<Car>

    @Insert
   suspend fun insertCar(car: Car)

    @Update
   suspend fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)
}