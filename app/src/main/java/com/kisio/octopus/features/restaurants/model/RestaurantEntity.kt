package com.kisio.octopus.features.restaurants.model

import com.google.gson.annotations.SerializedName

/*
`restaurant_id`, `restaurant_name`, `restaurant_address`, `restaurant_lat`, `restaurant_lng`, `restaurant_description`,
 `phone_number`, `time_schedules`, `food_type`, `restaurant_menu`, `food_mean_time` FROM `restaurant`
restaurant_lat & restaurant_lng : DOUBLE


 */

data class RestaurantEntity(@SerializedName("restaurant_id") val id : Long = -1,
                            @SerializedName("restaurant_name") val name : String = "",
                            @SerializedName("restaurant_address") val address : String = "",
                            @SerializedName("restaurant_lat") val lat: Double = 0.0,
                            @SerializedName("restaurant_lng") val lng : Double = 0.0,
                            @SerializedName("restaurant_description") val description : String = "",
                            @SerializedName("phone_number") val phoneNumber : String = "",
                            @SerializedName("time_schedules") val timeSchedules : String = "",
                            @SerializedName("food_type") val foodType : String = "",
                            @SerializedName("food_mean_time") val foodMeanTime : String = "",
                            @SerializedName("restaurant_rate") val rate : RestaurantRateEntity = RestaurantRateEntity.empty(),
                            @SerializedName("restaurant_pictures") val restaurantPictures : List<PictureEntity> = listOf()) {
    companion object {
        fun empty() = RestaurantEntity()
    }
}