package com.kisio.octopus.features.create.model

import com.kisio.octopus.core.exception.Failure

class CreateFailure {
    class CreateFailed: Failure.FeatureFailure()
}
