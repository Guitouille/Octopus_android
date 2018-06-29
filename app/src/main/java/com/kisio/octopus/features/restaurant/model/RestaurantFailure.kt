package com.kisio.octopus.features.restaurant.model

import com.kisio.octopus.core.exception.Failure

class RestaurantFailure {
    class NoRestaurantFound : Failure.FeatureFailure()
}
