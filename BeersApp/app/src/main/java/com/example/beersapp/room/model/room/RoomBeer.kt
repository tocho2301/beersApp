package com.example.beersapp.room.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomBeer(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "image_url")
    var image: String?,

    @ColumnInfo(name = "tagline")
    var tagline: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "first_brewed")
    var firstBrewed: String?,

    @ColumnInfo(name = "food_pairing")
    var foodPairing: String?
)
