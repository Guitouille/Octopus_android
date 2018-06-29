package com.kisio.octopus.features.create

import com.kisio.octopus.core.exception.Failure

class CreateFailure {
    class CreateFailed: Failure.FeatureFailure()
    class CheckFormFailed: Failure.FeatureFailure()
}
