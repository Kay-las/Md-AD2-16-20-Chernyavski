package com.demo.homework5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface CarDao {

    @Query("SELECT * FROM Car ORDER BY modelCar")
    fun getAllCar(): List<Car>
    @Query("SELECT * FROM Car ORDER BY modelCar")
    fun getAllCarRX(): Flowable<List<Car>>
    @Insert
    fun insertCar(car: Car)

    @Update
    fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)
}


