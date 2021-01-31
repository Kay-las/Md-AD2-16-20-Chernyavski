package com.demo.homework5.work

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Work(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,

        val carId: Int,
        var progressItem: Int,
        var nameWork: String?,
//        var data: String?,
        var cost: String?
         )