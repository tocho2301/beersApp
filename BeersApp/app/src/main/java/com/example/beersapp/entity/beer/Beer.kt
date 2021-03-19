package com.example.beersapp.entity.beer

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String?,

    @SerializedName("image_url")
    var image: String?,

    @SerializedName("tagline")
    var tagline: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("first_brewed")
    var firstBrewed: String?,

    @SerializedName("food_pairing")
    var foodPairing: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(tagline)
        parcel.writeString(description)
        parcel.writeString(firstBrewed)
        parcel.writeStringList(foodPairing)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Beer> {
        override fun createFromParcel(parcel: Parcel): Beer {
            return Beer(parcel)
        }

        override fun newArray(size: Int): Array<Beer?> {
            return arrayOfNulls(size)
        }
    }
}
