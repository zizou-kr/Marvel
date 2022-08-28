package com.zizou.marvel.data.util

import java.math.BigInteger
import java.security.MessageDigest

object EncryptUtil {
    fun getMD5(text: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(text.toByteArray())).toString(16).padStart(32, '0')
    }
}