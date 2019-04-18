package com.github.biancacristina.HomologationSystem.services.exceptions

import java.lang.RuntimeException

class ObjectNotFoundException(msg: String): RuntimeException(msg) {

    fun ObjectNotFoundException (msg: String, cause: Throwable) {
        return ObjectNotFoundException(msg, cause)
    }
}