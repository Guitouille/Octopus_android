package com.kisio.octopus.features.create.model

data class CreateStatusResponse(val status: Int) {
    companion object {
        fun empty() = CreateStatusResponse(0)
    }
}
