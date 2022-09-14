package com.shoppingapi.core.api.entity.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "DispatchRequest")
data class DispatchRequest(

    @field:Schema(name = "orderId")
    var orderId: String,

    @field:Schema(name = "userId")
    var userId: String,

    @field:Schema(name = "shipStatus", allowableValues = ["PENDING", "SHIPPED", "CANCELLED", "COMPLETED"])
    var shipStatus: String,

    @field:Schema(name = "lastUpdate")
    var lastUpdate: String,
)
