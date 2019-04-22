package com.github.biancacristina.HomologationSystem.domain.enums

enum class Perfil constructor(val cod: Int, val nome: String) {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    companion object {
        fun toEnum(cod: Int?): Perfil? {
            // Convert a cod to a Profile

            if (cod == null) return null

            for (i in Perfil.values()) {
                if (cod == i.cod) return i
            }

            throw IllegalArgumentException("Id invalido: $cod")
        }
    }
}