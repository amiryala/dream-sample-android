package com.dream.sample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Home (

    @SerializedName("id")
    val id: String,

    @SerializedName("listingType")
    val listingType: String = "for sale",

    @SerializedName("propertyType")
    val propertyType: String = "",

    @SerializedName("squareFeet")
    val squareFeet: Int = 0,

    @SerializedName("neighborhood")
    val neighborhood : String = "",

    @SerializedName("price")
    val price: Int = 0,

    @SerializedName("bathrooms")
    val numberOfBathroom: Float = 0f,

    @SerializedName("bedrooms")
    val numberOfBedroom: Int = 0,

    @SerializedName("latitude")
    val latitude: Double = 0.0,

    @SerializedName("longitude")
    val longitude: Double = 0.0,

    @SerializedName("streetNumber")
    val streetNumber: String = "",

    @SerializedName("streetName")
    val streetName : String = "",

    @SerializedName("city")
    val city: String = "",

    @SerializedName("stateCode")
    val stateCode : String = "",

    @SerializedName("zipCode")
    val zipCode : String = "",

    @SerializedName("numberOfPhotos")
    val numberOfPhotos: Int = 0,

    @SerializedName("photos")
    val photos: List<String>?
    ): Parcelable