package com.demo.homework5

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,

       var ownerNameCar: String?,
        var producerCar: String?,
        var modelCar: String?,
        var numberCar: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(ownerNameCar)
        parcel.writeString(producerCar)
        parcel.writeString(modelCar)
        parcel.writeString(numberCar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}







