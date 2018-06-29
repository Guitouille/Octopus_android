package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

data class RestaurantRateEntity(@SerializedName("rate_value") var rateValue : Float = -1f,
                                @SerializedName("rate_count") var rateCount : Int = -1) {
    companion object {
        fun empty() = RestaurantRateEntity()
    }
}