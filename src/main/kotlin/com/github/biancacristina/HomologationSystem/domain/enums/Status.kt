package com.github.biancacristina.HomologationSystem.domain.enums

enum class Status constructor(val cod: Int, val status: String) {
    APROVADO(1, "APROVADO"),
    REPROVADO(2, "REPROVADO"),
    ANDAMENTO(3, "EM ANDAMENTO");

    companion object {
        fun toEnum(cod: Int?): Status? {
            // Convert a cod to a Status

            if (cod == null) return null

            for (i in Status.values()) {
                if (cod == i.cod) return i
            }

            throw IllegalArgumentException("Id invalido: $cod")
        }
    }
}