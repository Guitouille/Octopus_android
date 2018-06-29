package com.kisio.octopus.features.connection.model

data class ConnectionStatusResponse(val status: Int) {
    companion object {
        fun empty() = ConnectionStatusResponse(0)
    }
}
