package com.scurab.appsandbox.model

import com.google.gson.annotations.SerializedName

class Person(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("car_make") val carBrand: String,
    @SerializedName("image") val imageUrl: String
)