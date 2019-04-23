package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.context.SecurityContextHolder



@Service
class CustomUserDetailsService: UserDetailsService{
    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    override fun loadUserByUsername(acesso: String): UserDetails {
        return CustomUserDetails(usuarioRepository.findOneByAcesso(acesso)!!)
    }

    fun authenticated(): CustomUserDetails? {
        try {
            return SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        } catch (e: Exception) {
            return null
        }

    }
}