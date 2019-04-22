package com.github.biancacristina.HomologationSystem.repositories

import com.github.biancacristina.HomologationSystem.domain.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findOneByAcesso(acesso: String): Usuario?
}