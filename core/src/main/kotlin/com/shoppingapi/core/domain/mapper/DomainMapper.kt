package com.shoppingapi.core.domain.mapper;

import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch
import com.shoppingapi.core.datastore.entity.postgres.Item
import com.shoppingapi.core.datastore.entity.postgres.Shop
import com.shoppingapi.core.datastore.entity.postgres.OnlineUser
import com.shoppingapi.core.datastore.entity.report.ShopReport
import com.shoppingapi.core.domain.entity.DispatchDTO
import com.shoppingapi.core.domain.entity.ItemDTO
import com.shoppingapi.core.domain.entity.ShopDTO
import com.shoppingapi.core.domain.entity.UserDTO
import com.shoppingapi.core.domain.entity.report.ShopReportDTO
import java.util.stream.Collectors

fun ShopDTO.toEntity(): Shop = Shop(
    id = this.id,
    userIdentifier = this.userIdentifier,
    total = this.total,
    createDate = this.createDate,
    items = this.items
        .stream()
        .map { it -> Item(
            price = it.price,
            productIdentifier = it.productIdentifier
        ) }
        .collect(Collectors.toList())
)

fun Shop.toDTO(): ShopDTO = ShopDTO(
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

fun UserDTO.toEntity(): OnlineUser = OnlineUser(
    cpf = this.cpf,
    name = this.name,
    address = this.address,
    email = this.email,
    phone = this.phone,
    createDate = this.createDate
)

fun OnlineUser.toDTO(): UserDTO = UserDTO(
    cpf = this.cpf,
    name = this.name,
    address = this.address,
    email = this.email,
    phone = this.phone,
    createDate = this.createDate
)

// Reports

fun ShopReport.toDTO(): ShopReportDTO = ShopReportDTO(
    count = this.count,
    total = this.total.toDouble(),
    mean = this.mean
)

fun ShopReportDTO.toDTO(): ShopReport = ShopReport(
    count = this.count,
    total = this.total,
    mean = this.mean
)

// Dispatches

fun Dispatch.toDTO(): DispatchDTO = DispatchDTO(
    orderId = this.orderId!!,
    userId = this.userId!!,
    shipStatus = this.shipStatus!!,
    lastUpdate = this.lastUpdate!!,
)

fun DispatchDTO.toEntity(): Dispatch = Dispatch(
    userId = this.userId,
    orderId = this.orderId,
    shipStatus = this.shipStatus,
    lastUpdate = this.lastUpdate,
)