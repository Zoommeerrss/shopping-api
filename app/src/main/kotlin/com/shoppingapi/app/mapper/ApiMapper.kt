package com.shoppingapi.app.mapper;

import com.shoppingapi.app.entity.api.request.ShopRequest
import com.shoppingapi.app.entity.api.request.UserRequest
import com.shoppingapi.app.entity.api.response.ItemResponse
import com.shoppingapi.app.entity.api.response.ShopResponse
import com.shoppingapi.app.entity.api.response.UserResponse
import com.shoppingapi.app.entity.report.response.ShopReportResponse
import com.shoppingapi.domain.entity.dto.ItemDTO
import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.entity.dto.UserDTO
import com.shoppingapi.domain.entity.report.ShopReportDTO
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
