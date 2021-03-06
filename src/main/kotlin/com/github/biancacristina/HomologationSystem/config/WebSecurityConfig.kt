package com.github.biancacristina.HomologationSystem.config

import com.github.biancacristina.HomologationSystem.security.JWTAuthenticationFilter
import com.github.biancacristina.HomologationSystem.security.JWTAuthorizationFilter
import com.github.biancacristina.HomologationSystem.security.JWTUtil
import com.github.biancacristina.HomologationSystem.services.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


@EnableWebSecurity
class WebSecurityConfig(private val customUserDetailsService: CustomUserDetailsService,
                        private val passwordEncoderAndMatcher: PasswordEncoder)
    : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/equipamentos/**").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/equipamentos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/equipamentos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/equipamentos/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()

        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil))
        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil, customUserDetailsService))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        // Libera o acesso aos meus endpoints para múltiplas fontes (ex: frontend)
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        configuration.allowedMethods = Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoderAndMatcher)
    }
}