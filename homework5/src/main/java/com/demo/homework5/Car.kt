package com.demo.homework5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
//        @PrimaryKey(autoGenerate = true) var id: Int,

        @PrimaryKey  var ownerNameCar: String?,
        var producerCar: String?,
        var modelCar: String?,
        var numberCar: String?
)







