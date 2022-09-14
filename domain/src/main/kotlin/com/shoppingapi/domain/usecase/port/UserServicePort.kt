package com.shoppingapi.domain.usecase.port

import com.shoppingapi.core.domain.entity.UserDTO
import org.springframework.data.domain.Page
import java.util.*

interface UserServicePort {

    fun listTotal(): Long
    fun findById(cpf: String): Optional<UserDTO>
    fun save(user: UserDTO): UserDTO
    fun deleteById(cpf: String)
    fun findAll(pageNo: Int, pageSize: Int, sortBy: String): Page<UserDTO>
    fun findByNameLike(name: String): List<UserDTO>
    fun findByNameContaining(name: String): List<UserDTO>
    fun countUserByCpf(cpf: String): Long
}