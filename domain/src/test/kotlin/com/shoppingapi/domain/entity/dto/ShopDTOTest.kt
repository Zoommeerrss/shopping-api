package com.shoppingapi.domain.entity.dto

import com.shoppingapi.core.domain.entity.ItemDTO
import com.shoppingapi.core.domain.entity.ShopDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class ShopDTOTest {

    @Test
    fun `validar campos Shop domain`() {

        val shop = ShopDTO(
            id = 1,
            userIdentifier = "teste",
            total = BigDecimal.ZERO,
            createDate = Date(),
            items = arrayListOf(ItemDTO(BigDecimal.ZERO, "teste"))
        )

        assertEquals(1, shop.id)
        assertEquals("teste", shop.userIdentifier)
        assertEquals(BigDecimal.ZERO, shop.total)
        assertNotNull(shop.items)
    }
}