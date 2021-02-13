package com.demo.homework5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.homework5.work.Work
import com.demo.homework5.work.WorkDao

@Database (entities = [Car::class, Work::class], version = 1)
abstract class DataBaseCar : RoomDatabase() {



    abstract fun getCarDao (): CarDao
    abstract fun getWorkDao (): WorkDao

    companion object {
        private var db: RoomDatabase? = null
        fun init (context: Context):DataBaseCar {
            if(db == null)
                db = Room.databaseBuilder(context, DataBaseCar::class.java, "database")
                        .build()
            return db as DataBaseCar
        }

    }
}