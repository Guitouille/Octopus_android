package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

/*
`restaurant_id`, `restaurant_name`, `restaurant_address`, `restaurant_lat`, `restaurant_lng`, `restaurant_description`,
 `phone_number`, `time_schedules`, `food_type`, `restaurant_menu`, `food_mean_time` FROM `restaurant`
restaurant_lat & restaurant_lng : DOUBLE


 */

data class RestaurantEntity(@SerializedName("restaurant_id") val id : Long,
                            @SerializedName("restaurant_address") val address : String,
                            @SerializedName("restaurant_lat") val lat: Double,
                            @SerializedName("restaurant_lng") val lng : Double,
                            @SerializedName("restaurant_description") val description : String,
                            @SerializedName("phone_number") val phoneNumber : String,
                            @SerializedName("time_schedules") val timeSchedules : String,
                            @SerializedName("food_type") val foodType : String,
                            @SerializedName("food_mean_time") val foodMeanTime : String)