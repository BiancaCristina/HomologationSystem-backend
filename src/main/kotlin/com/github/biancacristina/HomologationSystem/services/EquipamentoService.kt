package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EquipamentoService {

    @Autowired
    private lateinit var equipamentoRepository: EquipamentoRepository

    fun findById (id: Long): Equipamento {
        var obj = equipamentoRepository.findById(id).orElse(null)
        return obj
    }

    fun insert (obj: Equipamento): Equipamento {
        obj.id = 0

        return equipamentoRepository.save(obj)
    }

    fun delete (id: Long) {
        this.findById(id)
        equipamentoRepository.deleteById(id)

    }
}