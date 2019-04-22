package com.github.biancacristina.HomologationSystem.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.biancacristina.HomologationSystem.domain.enums.Perfil
import javax.persistence.*


@Entity
data class Usuario(
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Long,

    var acesso: String,
    var senha: String,

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    var perfis: MutableSet<Int> = HashSet()
) {
    init {
        perfis.add(Perfil.USER.cod)
    }

    @JsonIgnore
    fun getPerfisUsuario(): Set<Perfil?> {
        // Converte lista de inteiros em lista de perfis
        return perfis.map { x -> Perfil.toEnum(x) }.toSet()
    }
}

