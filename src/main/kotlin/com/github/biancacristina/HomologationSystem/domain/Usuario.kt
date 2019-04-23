package com.github.biancacristina.HomologationSystem.domain

import com.github.biancacristina.HomologationSystem.domain.enums.Perfil
import javax.persistence.*


@Entity
data class Usuario(
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Long,

    @Column(unique=true)
    var acesso: String,

    var senha: String,

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    var perfis: MutableSet<String> = HashSet()
) {
    init {
        perfis.add(Perfil.USER.nome)
    }
}

