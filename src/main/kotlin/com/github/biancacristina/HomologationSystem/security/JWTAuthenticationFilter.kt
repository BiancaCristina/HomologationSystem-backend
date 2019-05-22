package com.github.biancacristina.HomologationSystem.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.biancacristina.HomologationSystem.domain.Usuario
import com.github.biancacristina.HomologationSystem.services.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import javax.servlet.FilterChain
import javax.servlet.ServletResponse
import javax.servlet.ServletRequest






class JWTAuthenticationFilter(
    var authManager: AuthenticationManager,
    var jwtUtil: JWTUtil
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(
            req: HttpServletRequest,
            res: HttpServletResponse): Authentication? {
        try {
            val creds = ObjectMapper()
                    .readValue(req.inputStream, Usuario::class.java)

            val authToken = UsernamePasswordAuthenticationToken(creds.acesso, creds.senha, ArrayList())

            val auth: Authentication = authManager.authenticate(authToken)
            return auth
        }

        catch(e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse,
                                          chain: FilterChain?,
                                          auth: Authentication) {

        val username = (auth.principal as CustomUserDetails).usuario.acesso
        val token = jwtUtil.generateToken(username)
        res.addHeader("Authorization", "Bearer $token")
        res.setHeader("Access-Control-Expose-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
    }
}

