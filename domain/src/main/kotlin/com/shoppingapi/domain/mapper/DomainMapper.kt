package com.shoppingapi.domain.mapper;

import com.shoppingapi.datastore.dataprovider.entity.postgres.Item
import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import com.shoppingapi.datastore.dataprovider.entity.postgres.OnlineUser
import com.shoppingapi.datastore.dataprovider.report.ShopReport
import com.shoppingapi.domain.entity.dto.ItemDTO
import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.entity.dto.UserDTO
import com.shoppingapi.domain.entity.report.ShopReportDTO
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