package com.github.biancacristina.HomologationSystem.resources

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.dto.EquipamentoDTO
import com.github.biancacristina.HomologationSystem.services.EquipamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

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

    @RequestMapping(value=["/page"], method=[RequestMethod.GET])
    fun findAllPage(
        @RequestParam(value="page", defaultValue= "0") page: Int,
        @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
        @RequestParam(value="direction", defaultValue= "DESC") direction: String,
        @RequestParam(value="orderBy", defaultValue= "dataUltimaEdicao") orderBy: String
    ): ResponseEntity<Page<Equipamento>> {
        var lista: Page<Equipamento> = equipamentoService.findAllPage(page, linesPerPage, direction, orderBy)

        return ResponseEntity.ok().body(lista)
    }

    @RequestMapping(method=[RequestMethod.POST])
    fun insert(@RequestBody obj: Equipamento): ResponseEntity<Unit> {
        var obj = equipamentoService.insert(obj)

        // Retorna a URI do objeto criado
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.id).toUri()

        return ResponseEntity.created(uri).build()
    }

    @RequestMapping(value=["/{id}"], method= [RequestMethod.PUT])
    fun update(@Valid @RequestBody objDTO: EquipamentoDTO,
               @PathVariable id: Long): ResponseEntity<Unit> {
        equipamentoService.update(objDTO, id)

        return ResponseEntity.noContent().build()
    }

    @RequestMapping(value=["/{id}"], method= [RequestMethod.DELETE])
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        equipamentoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}