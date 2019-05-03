package com.github.biancacristina.HomologationSystem.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Equipamento (
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Long,

    var r12: Long?,

    var nome: String,
    var fabricante: String,
    var descricao: String,
    var status: String,

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    var dataCriacao: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    var dataUltimaEdicao: LocalDateTime = LocalDateTime.now()
)