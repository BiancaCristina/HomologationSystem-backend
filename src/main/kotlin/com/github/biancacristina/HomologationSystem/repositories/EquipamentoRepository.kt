package com.github.biancacristina.HomologationSystem.repositories

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipamentoRepository: JpaRepository<Equipamento,Long> {
}