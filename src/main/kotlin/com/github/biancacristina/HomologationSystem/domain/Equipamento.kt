package com.github.biancacristina.HomologationSystem.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Equipamento (
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Long,

    @Column(unique=true)
    var r12: Long?,

    var nome: String,
    var fabricante: String,
    var descricao: String,
    var status: String,

    var linkImagem: String,

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    var dataCriacao: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    var dataUltimaEdicao: LocalDateTime = LocalDateTime.now()
)