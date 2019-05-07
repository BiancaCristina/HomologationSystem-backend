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

        var eq4 = Equipamento(
                0,
                40004,
                "Copo DOT2015",
                "Algar",
                "Copo plástico verde",
                Status.APROVADO.status)

        var eq5 = Equipamento(
                0,
                50005,
                "Cadeira Gamer",
                "Husky",
                "Cadeira cara que faz a mesma coisa que qualquer outra",
                Status.REPROVADO.status)

        var eq6 = Equipamento(
                0,
                60006,
                "Garrafa Térmica",
                "TwistShake",
                "Boa para esportes",
                Status.ANDAMENTO.status)

        var eq7 = Equipamento(
                0,
                70007,
                "Cabo LAN",
                "Desconhecido",
                "Para conectar internet cabeada",
                Status.REPROVADO.status)

        var eq8 = Equipamento(
                0,
                80008,
                "Mousepad Goliathus",
                "Razer",
                "Mousepad bem grande",
                Status.ANDAMENTO.status)

        var eq9 = Equipamento(
                0,
                90009,
                "Black Stories",
                "Moses",
                "Jogo de adivinhação",
                Status.APROVADO.status)

        var eq10 = Equipamento(
                0,
                10000,
                "Folha Sulfite A4",
                "COPIMAX",
                "Folha normal",
                Status.REPROVADO.status)

        equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10))

        var user_admin = Usuario(0, "bianca", encoder.passwordEncoderConfiguration().encode("123"))
        user_admin.perfis.add(Perfil.ADMIN.nome)

        var user_user = Usuario(0, "outro", encoder.passwordEncoderConfiguration().encode("456"))

        usuarioRepository.saveAll(Arrays.asList(user_admin, user_user))
    }
}