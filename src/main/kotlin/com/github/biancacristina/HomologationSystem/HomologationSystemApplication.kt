package com.github.biancacristina.HomologationSystem

import com.github.biancacristina.HomologationSystem.domain.Equipamento
import com.github.biancacristina.HomologationSystem.domain.Usuario
import com.github.biancacristina.HomologationSystem.repositories.EquipamentoRepository
import com.github.biancacristina.HomologationSystem.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class HomologationSystemApplication: CommandLineRunner {
	@Autowired
	private lateinit var equipamentoRepository: EquipamentoRepository

	@Autowired
	private lateinit var usuarioRepository: UsuarioRepository

	override fun run(vararg args: String?) {
		var eq1 = Equipamento(
				0,
				10001,
				"SSD 240GB",
				"Kingston",
				"Um disco SSD 240GB",
				"Aprovado")

		var eq2 = Equipamento(
				0,
				20002,
				"Notebook Samsung X40 Expert",
				"Samsung",
				"Um notebook simples",
				"Em andamento")

		var eq3 = Equipamento(
				0,
				30003,
				"Zenfone 4 Selfie",
				"ASUS",
				"Celular razoável",
				"Recusado")

		equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3))

		var u1 = Usuario(0, "bianca", "123")

		usuarioRepository.save(u1)
	}

}

fun main(args: Array<String>) {
	runApplication<HomologationSystemApplication>(*args)
}
