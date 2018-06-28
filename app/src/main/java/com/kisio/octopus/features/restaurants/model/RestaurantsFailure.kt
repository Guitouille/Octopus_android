package com.kisio.octopus.features.restaurants.model

import com.kisio.octopus.core.exception.Failure

class RestaurantsFailure {
    class NoRestaurantFound : Failure.FeatureFailure()
}
