package com.shoppingapi.core.api.entity.request

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(name = "ItemRequest")
data class ItemRequest(

    @field:NotNull
    @field:Schema(name = "price")
    var price: BigDecimal,

    @field:NotEmpty
    @field:Schema(name = "productIdentifier")
    var productIdentifier: String,
)
