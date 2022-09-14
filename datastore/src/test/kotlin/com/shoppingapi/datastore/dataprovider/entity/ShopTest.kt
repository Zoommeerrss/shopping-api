package com.shoppingapi.datastore.dataprovider.entity

import com.shoppingapi.core.datastore.entity.postgres.Item
import com.shoppingapi.core.datastore.entity.postgres.Shop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class ShopTest {

    @Test
    fun `validar campos Shop entity`() {

        val shop = Shop(
            id = 1,
            userIdentifier = "teste",
            total = BigDecimal.ZERO,
            createDate = Date(),
            items = arrayListOf(Item(BigDecimal.ZERO, "teste"))
        )

        assertEquals(1, shop.id)
        assertEquals("teste", shop.userIdentifier)
        assertEquals(BigDecimal.ZERO, shop.total)
        assertNotNull(shop.items)
    }
}