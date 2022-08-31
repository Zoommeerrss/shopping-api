package com.shoppingapi.domain.usecase

import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import com.shoppingapi.datastore.usecase.port.ShopPort
import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.mapper.toDTO
import com.shoppingapi.domain.mapper.toEntity
import com.shoppingapi.domain.usecase.port.ShopServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
@Transactional
class ShopServiceImpl: ShopServicePort {

    @Autowired
    private lateinit var port: ShopPort

    override fun findTotal(): Long = port.findTotal()

    override fun countUserById(id: Long): Long = port.countShopById(id)

    override fun findAll(pageNo: Int, pageSize: Int, sortBy: String): Page<ShopDTO> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy))
        val results: Page<Shop> = port.findAll(paging)

        println("Invoices: " + port.findAll())

        return results.map(Shop::toDTO)
    }

    override fun findById(id: Long): Optional<ShopDTO> = port.findById(id).map(Shop::toDTO)

    override fun save(shop: ShopDTO): ShopDTO {
        val total: BigDecimal = shop.items
            .stream()
            .map { it -> it.price }
            .reduce(BigDecimal.ZERO, BigDecimal::add)

        shop.total = total

        return port.save(shop.toEntity()).toDTO()
    }

    override fun deleteById(id: Long) {
         port.deleteById(id)
    }

    override fun findAllByUserIdentifier(userIdentifier: String): List<ShopDTO> {
        return port.findAllByUserIdentifier(userIdentifier)
            .stream()
            .map (Shop::toDTO)
            .collect(Collectors.toList())
    }

    override fun findAllByTotalGreaterThan(total: BigDecimal): List<ShopDTO> {
        return port.findAllByTotalGreaterThan(total)
            .stream()
            .map (Shop::toDTO)
            .collect(Collectors.toList())
    }

    override fun findAllByDateCreatedGreaterThanEquals(dateCreated: Date): List<ShopDTO> {
        return port.findAllByDateCreatedGreaterThanEquals(dateCreated)
            .stream()
            .map (Shop::toDTO)
            .collect(Collectors.toList())
    }
}