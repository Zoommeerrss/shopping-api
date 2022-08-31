package com.shoppingapi.app.entity.api.response

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class ShopResponseTest {

    @Test
    fun `validar campos Shop app`() {

        val shop = ShopResponse(
            id = 1,
            userIdentifier = "teste",
            total = BigDecimal.ZERO,
            createDate = Date(),
            items = arrayListOf(ItemResponse(BigDecimal.ZERO, "teste"))
        )

        assertEquals(1, shop.id)
        assertEquals("teste", shop.userIdentifier)
        assertEquals(BigDecimal.ZERO, shop.total)
        assertNotNull(shop.items)
    }
}