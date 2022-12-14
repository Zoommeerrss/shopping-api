package com.shoppingapi.datastore.usecase.port

import com.shoppingapi.core.datastore.entity.postgres.Shop
import com.shoppingapi.core.datastore.entity.report.ShopReport
import java.math.BigDecimal
import java.util.*

interface ReportPort {
    fun getShopByFilters(dataInicio: Date?, dataFim: Date?, valorMinimo: BigDecimal?): List<Shop>
    fun getReportByDate(dataInicio: Date?, dataFim: Date?): ShopReport?
}