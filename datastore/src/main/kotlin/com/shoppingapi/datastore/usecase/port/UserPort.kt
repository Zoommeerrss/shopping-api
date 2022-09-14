package com.shoppingapi.datastore.usecase.port

import com.shoppingapi.core.datastore.entity.postgres.OnlineUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserPort {

    fun listTotal(): Long
    fun findById(cpf: String): Optional<OnlineUser>
    fun save(onlineUser: OnlineUser): OnlineUser
    fun deleteById(cpf: String)
    fun findAll(pageable: Pageable): Page<OnlineUser>
    fun findByNameLike(name: String): List<OnlineUser>
    fun findByNameContaining(name: String): List<OnlineUser>
    fun countUserByCpf(cpf: String): Long
}