package com.shoppingapi.domain.entity.dto

import java.math.BigDecimal
import java.util.*

data class ShopDTO(

    var id: Long,

    var userIdentifier: String,

    var total: BigDecimal,

    var createDate: Date,

    var items: List<ItemDTO>
)
