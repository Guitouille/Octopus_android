package com.kisio.octopus.core.extension

import java.security.MessageDigest

fun String.sha256() : String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(this.toByteArray(charset("UTF-8")))
            val hexString = StringBuffer()

            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }

            return hexString.toString()
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }

}