package com.github.biancacristina.HomologationSystem.services

import com.github.biancacristina.HomologationSystem.config.PasswordEncoderConfig
import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.domain.Usuario
import com.github.biancacristina.HomologationSystem.domain.enums.Perfil
import com.github.biancacristina.HomologationSystem.domain.enums.Status
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import com.github.biancacristina.HomologationSystem.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class DBService {

    @Autowired
    private lateinit var equipamentoRepository: EquipamentoRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var encoder: PasswordEncoderConfig

    fun instantiateTestDataBase() {
        var eq1 = Equipamento(
                0,
                10001,
                "SSD 240GB",
                "Kingston",
                "Um disco SSD 240GB",
                Status.APROVADO.status)

        var eq2 = Equipamento(
                0,
                20002,
                "Notebook Samsung X40 Expert",
                "Samsung",
                "Um notebook simples",
                Status.ANDAMENTO.status)

        var eq3 = Equipamento(
                0,
                30003,
                "Zenfone 4 Selfie",
                "ASUS",
                "Celular razoável",
                Status.REPROVADO.status)

        equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3))

        var user_admin = Usuario(0, "bianca", encoder.passwordEncoderConfiguration().encode("123"))
        user_admin.perfis.add(Perfil.ADMIN.nome)

        var user_user = Usuario(0, "outro", encoder.passwordEncoderConfiguration().encode("456"))

        usuarioRepository.saveAll(Arrays.asList(user_admin, user_user))
    }
}