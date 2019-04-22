package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Usuario
import com.github.biancacristina.HomologationSystem.repositories.UsuarioRepository
import com.github.biancacristina.HomologationSystem.services.exceptions.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    fun findById (id: Long): Usuario {
        var obj = usuarioRepository.findById(id)

        return obj.orElseThrow { ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + " Tipo: " + Usuario::class.qualifiedName
        )}
    }
}