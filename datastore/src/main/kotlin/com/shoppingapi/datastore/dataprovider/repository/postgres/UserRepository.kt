package com.shoppingapi.datastore.dataprovider.repository.postgres

import com.shoppingapi.datastore.dataprovider.entity.postgres.OnlineUser
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: PagingAndSortingRepository<OnlineUser, String> {

    fun findByNameLike(name: String): List<OnlineUser>
    fun findByNameContaining(name: String): List<OnlineUser>
    fun countOnlineUserByCpf(cpf: String): Long
}