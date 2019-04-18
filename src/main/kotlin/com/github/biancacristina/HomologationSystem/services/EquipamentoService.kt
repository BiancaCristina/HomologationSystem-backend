package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.dto.EquipamentoDTO
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import com.github.biancacristina.HomologationSystem.services.exceptions.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
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