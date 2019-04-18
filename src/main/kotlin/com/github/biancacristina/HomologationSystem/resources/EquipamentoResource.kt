package com.github.biancacristina.HomologationSystem.resources

import com.github.biancacristina.HomologationSystem.services.EquipamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/equipamentos"])
class EquipamentoResource {

    @Autowired
    private lateinit var equipamentoService: EquipamentoService

    @RequestMapping(value=["{id}"], method= [RequestMethod.GET])
    fun find(@PathVariable id: Long): ResponseEntity<*> {
        var obj = equipamentoService.findById(id)
        return ResponseEntity.ok().body(obj)
    }
}