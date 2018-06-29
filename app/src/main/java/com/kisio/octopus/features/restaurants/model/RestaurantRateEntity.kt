package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

data class RestaurantRateEntity(@SerializedName("rate_value") val rateValue : Float,
                                @SerializedName("rate_count") val rateCount : Int)