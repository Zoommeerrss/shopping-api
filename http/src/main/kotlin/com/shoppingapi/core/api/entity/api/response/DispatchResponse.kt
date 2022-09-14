package com.shoppingapi.core.api.entity.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "DispatchResponse")
data class DispatchResponse(

    @field:Schema(name = "orderId")
    var orderId: String,

    @field:Schema(name = "userId")
    var userId: String,

    @field:Schema(name = "shipStatus")
    var shipStatus: String,

    @field:Schema(name = "lastUpdate")
    var lastUpdate: String,
)
