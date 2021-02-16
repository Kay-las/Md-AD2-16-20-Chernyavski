package com.demo.homework5.work

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.chrono.ChronoLocalDateTime


@Entity
data class Work(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,

        val carId: Int,
        var progressItem: Int,
        var nameWork: String?,
        var cost: String?,
        var description: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(carId)
        parcel.writeInt(progressItem)
        parcel.writeString(nameWork)
        parcel.writeString(cost)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }

}