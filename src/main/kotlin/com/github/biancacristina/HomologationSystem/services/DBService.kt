package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class DBService {

    @Autowired
    private lateinit var equipamentoRepository: EquipamentoRepository

    fun instantiateTestDataBase(): Unit {
        var eq1 = Equipamento(
                0,
                10001,
                "SSD 240GB",
                "Kingston",
                "Um disco SSD 240GB",
                "Aprovado")

        var eq2 = Equipamento(
                0,
                20002,
                "Notebook Samsung X40 Expert",
                "Samsung",
                "Um notebook simples",
                "Em andamento")

        var eq3 = Equipamento(
                0,
                30003,
                "Zenfone 4 Selfie",
                "ASUS",
                "Celular razoável",
                "Recusado")

        equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3))
    }
}