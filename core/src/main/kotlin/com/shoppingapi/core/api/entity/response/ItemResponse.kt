package com.shoppingapi.core.api.entity.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "ItemResponse")
data class ItemResponse(

    @field:Schema(name = "price")
    var price: BigDecimal,

    @field:Schema(name = "productIdentifier")
    var productIdentifier: String,
)
