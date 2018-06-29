package com.kisio.octopus.features.connection

import com.kisio.octopus.core.exception.Failure

class ConnectionFailure {
    class UserDoNotExist: Failure.FeatureFailure()
}
