package com.shoppingapi.core.api.entity.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.*

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "ShopResponse")
data class ShopResponse(

    @field:Schema(name = "id")
    var id: Long,

    @field:Schema(name = "userIdentifier")
    var userIdentifier: String,

    @field:Schema(name = "total")
    var total: BigDecimal,

    @field:Schema(name = "createDate")
    var createDate: Date,

    @field:Schema(name = "items")
    var items: List<ItemResponse>
)
