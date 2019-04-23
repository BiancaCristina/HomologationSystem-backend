package com.github.biancacristina.HomologationSystem.resources

import com.github.biancacristina.HomologationSystem.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/usuarios"])
class UsuarioResource {

    @Autowired
    private lateinit var usuarioService: UsuarioService

    @RequestMapping(value=["{id}"], method= [RequestMethod.GET])
    fun find(@PathVariable id: Long): ResponseEntity<*> {
        var obj = usuarioService.findById(id)
        return ResponseEntity.ok().body(obj)
    }
}