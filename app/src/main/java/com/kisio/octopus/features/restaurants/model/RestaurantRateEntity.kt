package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

data class RestaurantRateEntity(@SerializedName("rate_value") val rateValue : Double,
                                @SerializedName("rate_count") val rateCount : Int)