package com.shoppingapi.core.domain.entity

data class DispatchDTO(

    var orderId: String,

    var userId: String,

    var shipStatus: String,

    var lastUpdate: String,
)
