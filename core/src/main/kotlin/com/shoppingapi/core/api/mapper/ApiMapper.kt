package com.shoppingapi.core.api.mapper;

import com.shoppingapi.core.api.entity.api.response.DispatchResponse
import com.shoppingapi.core.api.entity.api.response.ItemResponse
import com.shoppingapi.core.api.entity.api.response.ShopResponse
import com.shoppingapi.core.api.entity.api.response.UserResponse
import com.shoppingapi.core.api.entity.report.response.ShopReportResponse
import com.shoppingapi.core.api.entity.request.DispatchRequest
import com.shoppingapi.core.api.entity.request.ShopRequest
import com.shoppingapi.core.api.entity.request.UserRequest
import com.shoppingapi.core.domain.entity.DispatchDTO
import com.shoppingapi.core.domain.entity.ItemDTO
import com.shoppingapi.core.domain.entity.ShopDTO
import com.shoppingapi.core.domain.entity.UserDTO
import com.shoppingapi.core.domain.entity.report.ShopReportDTO
import java.util.stream.Collectors

fun ShopDTO.toResponse(): ShopResponse = ShopResponse(
    id = this.id,
    userIdentifier = this.userIdentifier,
    total = this.total,
    createDate = this.createDate,
    items = this.items
        .stream()
        .map { it -> ItemResponse(
            price = it.price,
            productIdentifier = it.productIdentifier
        ) }
        .collect(Collectors.toList())
)

fun ShopRequest.toDTO(): ShopDTO = ShopDTO(
    id = this.id,
    userIdentifier = this.userIdentifier,
    total = this.total,
    createDate = this.createDate,
    items = this.items
        .stream()
        .map { it -> ItemDTO(
            price = it.price,
            productIdentifier = it.productIdentifier
        ) }
        .collect(Collectors.toList())
)

// Users

fun UserDTO.toResponse(): UserResponse = UserResponse(
    cpf = this.cpf,
    name = this.name,
    address = this.address,
    email = this.email,
    phone = this.phone,
    createDate = this.createDate
)

fun UserRequest.toDTO(): UserDTO = UserDTO(
    cpf = this.cpf,
    name = this.name,
    address = this.address,
    email = this.email,
    phone = this.phone,
    createDate = this.createDate
)

// Reports

fun ShopReportDTO.toResponse(): ShopReportResponse = ShopReportResponse(
    count = this.count,
    total = this.total,
    mean = this.mean
)

// Dispatches

fun DispatchRequest.toDTO(): DispatchDTO = DispatchDTO(
    orderId = this.orderId,
    userId = this.userId,
    shipStatus = this.shipStatus,
    lastUpdate = this.lastUpdate,
)

fun DispatchDTO.toResponse(): DispatchResponse = DispatchResponse(
    orderId = this.orderId,
    userId = this.userId,
    shipStatus = this.shipStatus,
    lastUpdate = this.lastUpdate,
)
