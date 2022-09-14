package com.shoppingapi.core.api.entity.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(name = "ShopRequest")
data class ShopRequest(

    @field:Schema(name = "id")
    val id: Long,

    @field:NotEmpty
    @field:Schema(name = "userIdentifier")
    var userIdentifier: String,

    @field:NotNull
    @field:Schema(name = "total")
    var total: BigDecimal,

    @field:NotNull
    @field:Schema(name = "createDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    var createDate: Date,

    @field:NotNull
    @field:Schema(name = "items")
    var items: List<ItemRequest>
)
