package com.shoppingapi.domain.usecase.port

import com.shoppingapi.domain.entity.dto.ShopDTO
import org.springframework.data.domain.Page
import java.math.BigDecimal
import java.util.*

interface ShopServicePort {

    fun findTotal(): Long
    fun countUserById(id: Long): Long
    fun findById(id: Long): Optional<ShopDTO>
    fun save(shop: ShopDTO): ShopDTO
    fun deleteById(id: Long)
    fun findAll(pageNo: Int, pageSize: Int, sortBy: String): Page<ShopDTO>
    fun findAllByUserIdentifier(userIdentifier: String): List<ShopDTO>
    fun findAllByTotalGreaterThan(total: BigDecimal): List<ShopDTO>
    fun findAllByDateCreatedGreaterThanEquals(dateCreated: Date): List<ShopDTO>
}