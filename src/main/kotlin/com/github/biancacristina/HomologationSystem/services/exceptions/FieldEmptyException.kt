package com.github.biancacristina.HomologationSystem.services.exceptions

import java.lang.RuntimeException

class FieldEmptyException(msg: String): RuntimeException(msg) {

    fun FieldEmptyException (msg: String, cause: Throwable) {
        return FieldEmptyException(msg, cause)
    }
}