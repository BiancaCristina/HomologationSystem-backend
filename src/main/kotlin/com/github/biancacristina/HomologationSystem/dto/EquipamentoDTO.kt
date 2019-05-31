package com.github.biancacristina.HomologationSystem.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class EquipamentoDTO (
    var id: Long,
    var r12: Long?,
    var nome: String?,
    var fabricante: String?,
    var descricao: String?,
    var status: String?,
    var linkImagem: String?,

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    var dataUltimaEdicao: LocalDateTime = LocalDateTime.now()

)