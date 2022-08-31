package com.shoppingapi.datastore.usecase.port

import com.shoppingapi.datastore.dataprovider.entity.dynamodb.Invoice
import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.util.*

interface ShopPort {

    fun findTotal(): Long
    fun countShopById(id: Long): Long
    fun findById(id: Long): Optional<Shop>
    fun save(shop: Shop): Shop
    fun deleteById(id: Long)
    fun findAll(pageable: Pageable): Page<Shop>
    fun findAllByUserIdentifier(userIdentifier: String): List<Shop>
    fun findAllByTotalGreaterThan(total: BigDecimal): List<Shop>
    fun findAllByDateCreatedGreaterThanEquals(dateCreated: Date): List<Shop>

    // invoice
    fun findAll(): List<Invoice?>?
}