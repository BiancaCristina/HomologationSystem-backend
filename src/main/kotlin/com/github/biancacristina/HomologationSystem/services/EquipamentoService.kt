package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.dto.EquipamentoDTO
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import com.github.biancacristina.HomologationSystem.services.exceptions.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class EquipamentoService {

    @Autowired
    private lateinit var equipamentoRepository: EquipamentoRepository

    fun findById (id: Long): Equipamento {
        var obj = equipamentoRepository.findById(id)

        return obj.orElseThrow { ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + " Tipo: " + Equipamento::class.qualifiedName
        )}
    }

    fun findByR12(
        r12: Long?,
        page: Int,
        linesPerPage: Int): Page<Equipamento> {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.by("dataUltimaEdicao"))

        return equipamentoRepository.findByR12(r12, pageRequest)
    }

    fun findByNome(
        nome: String,
        page: Int,
        linesPerPage: Int
    ): Page<Equipamento> {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.by("dataUltimaEdicao"))

        return equipamentoRepository.findByNomeContainingIgnoreCase(nome, pageRequest)
    }

    fun findByFabricante(
            fabricante: String,
            page: Int,
            linesPerPage: Int
    ): Page<Equipamento> {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.by("dataUltimaEdicao"))

        return equipamentoRepository.findByFabricanteContainingIgnoreCase(fabricante, pageRequest)
    }

    fun findByDescricao(
            descricao: String,
            page: Int,
            linesPerPage: Int
    ): Page<Equipamento> {
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.by("dataUltimaEdicao"))

        return equipamentoRepository.findByDescricaoContainingIgnoreCase(descricao, pageRequest)
    }

    fun findAllPage(
        page: Int,
        linesPerPage: Int,
        direction: String,
        orderBy: String
    ): Page<Equipamento> {
        var pageRequest: PageRequest? = null

        if (direction == "DESC") {
            pageRequest = PageRequest.of(page, linesPerPage, Sort.by(orderBy).descending())
        }

        else if (direction == "ASC") {
            pageRequest = PageRequest.of(page, linesPerPage, Sort.by(orderBy).ascending())
        }

        return equipamentoRepository.findAll(pageRequest!!)
    }

    fun insert (obj: Equipamento): Equipamento {
        obj.id = 0

        return equipamentoRepository.save(obj)
    }

    fun update (objDTO: EquipamentoDTO,
                id: Long): Equipamento {
        var obj = this.findById(id)
        this.updateData(objDTO, obj)
        return equipamentoRepository.save(obj)
    }

    protected fun updateData(objDTO: EquipamentoDTO, obj: Equipamento) {
        obj.nome = objDTO.nome?:obj.nome
        obj.fabricante = objDTO.fabricante?:obj.fabricante
        obj.descricao = objDTO.descricao?:obj.descricao
        obj.dataUltimaEdicao = objDTO.dataUltimaEdicao
    }

    fun delete (id: Long) {
        this.findById(id)
        equipamentoRepository.deleteById(id)

    }
}