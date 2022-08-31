package com.shoppingapi.domain.entity.dto

import java.math.BigDecimal

data class ItemDTO(

    var price: BigDecimal,

    var productIdentifier: String,
)
