package com.shoppingapi.datastore.usecase


import com.shoppingapi.core.datastore.entity.postgres.OnlineUser
import com.shoppingapi.datastore.dataprovider.repository.postgres.UserRepository
import com.shoppingapi.datastore.usecase.port.UserPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserPortImpl: UserPort {

    @Autowired
    private lateinit var repository: UserRepository

    override fun listTotal(): Long = repository.count()

    override fun findById(cpf: String) = repository.findById(cpf)

    override fun save(onlineUser: OnlineUser): OnlineUser = repository.save(onlineUser)

    override fun deleteById(cpf: String) {
        repository.deleteById(cpf)
    }

    override fun findAll(pageable: Pageable): Page<OnlineUser> {
        return repository.findAll(pageable)
    }

    override fun findByNameLike(name: String): List<OnlineUser> {
        return repository.findByNameLike(name)
    }

    override fun findByNameContaining(name: String): List<OnlineUser> {
        return repository.findByNameContaining(name)
    }

    override fun countUserByCpf(cpf: String): Long {
        return repository.countOnlineUserByCpf(cpf)
    }
}