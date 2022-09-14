package com.shoppingapi.core.domain.entity

import java.math.BigDecimal

data class ItemDTO(

    var price: BigDecimal,

    var productIdentifier: String,
)
