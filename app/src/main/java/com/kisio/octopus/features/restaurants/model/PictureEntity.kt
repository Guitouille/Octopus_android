package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

/*
*picture_id*, *restaurant_id*, *picture_url*
 */
data class PictureEntity(@SerializedName("picture_id") val id : Long,
                         @SerializedName("restaurant_id") val restaurantId : Long,
                         @SerializedName("picture_url") val url : String)