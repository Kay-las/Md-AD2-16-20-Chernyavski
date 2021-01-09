package com.demo.homework5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Car::class], version = 1)
abstract class DataBaseCar : RoomDatabase() {

    abstract fun getCarDao (): CarDao

    companion object {
        fun init (context: Context) =
                Room.databaseBuilder(context, DataBaseCar::class.java, "database")
                        .allowMainThreadQueries()
                        .build()

    }
}