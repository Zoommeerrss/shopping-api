package com.shoppingapi.domain.usecase

import com.shoppingapi.core.datastore.entity.postgres.OnlineUser
import com.shoppingapi.datastore.usecase.port.UserPort
import com.shoppingapi.core.domain.entity.UserDTO
import com.shoppingapi.core.domain.mapper.toDTO
import com.shoppingapi.core.domain.mapper.toEntity
import com.shoppingapi.domain.usecase.port.UserServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceImpl: UserServicePort {

    @Autowired
    private lateinit var port: UserPort

    override fun listTotal(): Long = port.listTotal()

    override fun findAll(pageNo: Int, pageSize: Int, sortBy: String): Page<UserDTO> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy))
        val results: Page<OnlineUser> = port.findAll(paging)

        return results.map(OnlineUser::toDTO)
    }

    override fun findByNameLike(name: String): List<UserDTO> {
        val results: List<OnlineUser> = port.findByNameLike(name)

        return results
            .stream()
            .map(OnlineUser::toDTO)
            .collect(Collectors.toList())
    }

    override fun findByNameContaining(name: String): List<UserDTO> {
        val results: List<OnlineUser> = port.findByNameContaining(name)

        return results
            .stream()
            .map(OnlineUser::toDTO)
            .collect(Collectors.toList())
    }

    override fun findById(cpf: String): Optional<UserDTO> = port.findById(cpf).map(OnlineUser::toDTO)

    override fun save(user: UserDTO): UserDTO = port.save(user.toEntity()).toDTO()

    override fun deleteById(cpf: String) {
        port.deleteById(cpf)
    }

    override fun countUserByCpf(cpf: String): Long = port.countUserByCpf(cpf)
}