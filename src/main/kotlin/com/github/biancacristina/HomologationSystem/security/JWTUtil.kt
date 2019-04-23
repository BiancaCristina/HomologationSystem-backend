package com.github.biancacristina.HomologationSystem.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*


@Component
class JWTUtil {
    val secret = "SequenciaAssinarToken"
    val expiration: Long = 1000000000

    fun generateToken(username: String): String {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret!!.toByteArray())
                .compact()
    }

    fun tokenValido(token: String): Boolean {
        val claims = getClaims(token)
        if (claims != null) {
            val username = claims.subject
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true
            }
        }
        return false
    }

    fun getUsername(token: String): String? {
        val claims = getClaims(token)
        return claims?.subject
    }

    private fun getClaims(token: String): Claims? {
        try {
            return Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).getBody()
        }

        catch (e: Exception) {
            return null
        }

    }
}