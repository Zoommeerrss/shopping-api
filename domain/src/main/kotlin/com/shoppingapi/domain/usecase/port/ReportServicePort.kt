package com.shoppingapi.domain.usecase.port

import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.entity.report.ShopReportDTO
import java.math.BigDecimal
import java.util.*

interface ReportServicePort {
    fun getShopByFilters(dataInicio: Date?, dataFim: Date?, valorMinimo: BigDecimal?): List<ShopDTO>
    fun getReportByDate(dataInicio: Date?, dataFim: Date?): ShopReportDTO?
}