package com.github.biancacristina.HomologationSystem.security

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    fun generateToken(username: String): String {

        val secret = "SequenciaAssinarToken"
        val expiration: Long = 8000000

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret!!.toByteArray())
                .compact()
    }
}