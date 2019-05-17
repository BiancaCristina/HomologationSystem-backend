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

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["{id}"], method= [RequestMethod.GET])
    fun find(@PathVariable id: Long): ResponseEntity<*> {
        var obj = equipamentoService.findById(id)
        return ResponseEntity.ok().body(obj)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/searchR12"], method=[RequestMethod.GET])
    fun findByR12(
        @RequestParam(value="page", defaultValue= "0") page: Int,
        @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
        @RequestParam(value="r12", defaultValue="") r12: Long?
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findByR12(r12, page, linesPerPage)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/searchNome"], method=[RequestMethod.GET])
    fun findByNome(
        @RequestParam(value="page", defaultValue= "0") page: Int,
        @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
        @RequestParam(value="nome", defaultValue= "") nome: String
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findByNome(nome, page, linesPerPage)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/searchFabricante"], method=[RequestMethod.GET])
    fun findByFabricante(
            @RequestParam(value="page", defaultValue= "0") page: Int,
            @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
            @RequestParam(value="fabricante", defaultValue= "") fabricante: String
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findByFabricante(fabricante, page, linesPerPage)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/searchDescricao"], method=[RequestMethod.GET])
    fun findByDescricao(
            @RequestParam(value="page", defaultValue= "0") page: Int,
            @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
            @RequestParam(value="descricao", defaultValue= "") descricao: String
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findByDescricao(descricao, page, linesPerPage)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @RequestMapping(value=["/searchStatus"], method=[RequestMethod.GET])
    fun findByStatus(
            @RequestParam(value="page", defaultValue= "0") page: Int,
            @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
            @RequestParam(value="status", defaultValue= "") status: String
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findByStatus(status, page, linesPerPage)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(method=[RequestMethod.GET])
    fun findAll(): ResponseEntity<List<Equipamento>> {
        var lista: List<Equipamento> = equipamentoService.findAll()

        return ResponseEntity.ok().body(lista)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/page"], method=[RequestMethod.GET])
    fun findAllPage(
        @RequestParam(value="page", defaultValue= "0") page: Int,
        @RequestParam(value="linesPerPage", defaultValue= "10") linesPerPage: Int,
        @RequestParam(value="direction", defaultValue= "DESC") direction: String,
        @RequestParam(value="orderBy", defaultValue= "dataUltimaEdicao") orderBy: String
    ): ResponseEntity<Page<Equipamento>> {
        var listaPaginada: Page<Equipamento> = equipamentoService.findAllPage(page, linesPerPage, direction, orderBy)

        return ResponseEntity.ok().body(listaPaginada)
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(method=[RequestMethod.POST])
    fun insert(@Valid @RequestBody obj: Equipamento): ResponseEntity<Unit> {
        var obj = equipamentoService.insert(obj)

        // Retorna a URI do objeto criado
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.id).toUri()

        return ResponseEntity.created(uri).build()
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/{id}"], method= [RequestMethod.PUT])
    fun update(@Valid @RequestBody objDTO: EquipamentoDTO,
               @PathVariable id: Long): ResponseEntity<Unit> {
        equipamentoService.update(objDTO, id)

        return ResponseEntity.noContent().build()
    }

    @CrossOrigin("http://localhost:8081")
    @RequestMapping(value=["/{id}"], method= [RequestMethod.DELETE])
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        equipamentoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}