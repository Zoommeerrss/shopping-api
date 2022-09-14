package com.shoppingapi.datastore.usecase

import com.shoppingapi.core.datastore.entity.postgres.Shop
import com.shoppingapi.datastore.dataprovider.repository.postgres.ShopRepository
import com.shoppingapi.datastore.usecase.port.ShopPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ShopPortImpl: ShopPort {

    @Autowired
    private lateinit var shopRepository: ShopRepository

    override fun findTotal(): Long = shopRepository.count()

    override fun countShopById(id: Long): Long = shopRepository.countShopById(id)

    override fun findById(id: Long): Optional<Shop> = shopRepository.findById(id)

    override fun save(shop: Shop): Shop = shopRepository.save(shop)

    override fun deleteById(id: Long) {
        shopRepository.deleteById(id)
    }

    override fun findAll(pageable: Pageable): Page<Shop> = shopRepository.findAll(pageable)

    override fun findAllByUserIdentifier(userIdentifier: String): List<Shop> =
        shopRepository.findByUserIdentifier(userIdentifier)

    override fun findAllByTotalGreaterThan(total: BigDecimal): List<Shop> =
        shopRepository.findByTotalGreaterThan(total)

    override fun findAllByDateCreatedGreaterThanEquals(dateCreated: Date): List<Shop> = shopRepository.findByCreateDateGreaterThanEqual(dateCreated)

}