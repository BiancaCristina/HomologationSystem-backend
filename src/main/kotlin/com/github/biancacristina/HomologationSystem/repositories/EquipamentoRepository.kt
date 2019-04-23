package com.github.biancacristina.HomologationSystem.repositories

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipamentoRepository: JpaRepository<Equipamento,Long> {
    fun findByR12(r12: Long?, pageable: Pageable): Page<Equipamento>
    fun findByNomeContainingIgnoreCase(nome: String, pageable: Pageable): Page<Equipamento>
    fun findByFabricanteContainingIgnoreCase(fabricante: String, pageable: Pageable): Page<Equipamento>
    fun findByDescricaoContainingIgnoreCase(descricao: String, pageable: Pageable): Page<Equipamento>
}