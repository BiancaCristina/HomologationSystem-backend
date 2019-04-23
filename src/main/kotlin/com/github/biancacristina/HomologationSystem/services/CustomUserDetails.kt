package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Usuario
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

open class CustomUserDetails(
    var usuario: Usuario
): UserDetails {

    private val log = LoggerFactory.getLogger(CustomUserDetails::class.java)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return usuario.perfis.stream().map {
            role -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return usuario.acesso
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return usuario.senha
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}