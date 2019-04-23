package com.github.biancacristina.HomologationSystem.resources

import com.github.biancacristina.HomologationSystem.security.JWTUtil
import com.github.biancacristina.HomologationSystem.services.CustomUserDetails
import com.github.biancacristina.HomologationSystem.services.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value=["/auth"])
class AuthResource {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @RequestMapping(value=["/refreshToken"], method=[RequestMethod.POST])
    fun refreshToken(response: HttpServletResponse): ResponseEntity<Unit> {
        var user: CustomUserDetails = customUserDetailsService.authenticated()!!
        var novoToken = jwtUtil.generateToken(user.usuario.acesso)
        response.addHeader("Authorization", "Bearer " + novoToken)

        return ResponseEntity.noContent().build()
    }
}