package com.kisio.octopus.features.connection.model

import com.kisio.octopus.core.exception.Failure

class ConnectionFailure {
    class UserDoNotExist: Failure.FeatureFailure()
}
