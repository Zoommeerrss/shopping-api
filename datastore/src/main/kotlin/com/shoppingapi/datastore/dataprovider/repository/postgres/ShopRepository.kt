package com.shoppingapi.datastore.dataprovider.repository.postgres

import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface ShopRepository: PagingAndSortingRepository<Shop, Long> {

    fun findByUserIdentifier(userIdentifier: String): List<Shop>
    fun findByTotalGreaterThan(total: BigDecimal): List<Shop>
    fun findByCreateDateGreaterThanEqual(dateCreated: Date): List<Shop>
    fun countShopById(id: Long): Long
}