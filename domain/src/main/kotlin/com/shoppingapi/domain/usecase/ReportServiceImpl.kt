package com.shoppingapi.domain.usecase

import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import com.shoppingapi.datastore.usecase.port.ReportPort
import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.entity.report.ShopReportDTO
import com.shoppingapi.domain.mapper.toDTO
import com.shoppingapi.domain.usecase.port.ReportServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors

@Service
class ReportServiceImpl: ReportServicePort {

    @Autowired
    private lateinit var port: ReportPort

    override fun getShopByFilters(dataInicio: Date?, dataFim: Date?, valorMinimo: BigDecimal?): List<ShopDTO> {
        return port.getShopByFilters(dataInicio, dataFim, valorMinimo)
            .stream()
            .map(Shop::toDTO)
            .collect(Collectors.toList())
    }

    override fun getReportByDate(dataInicio: Date?, dataFim: Date?): ShopReportDTO? {

        return port.getReportByDate(dataInicio, dataFim)?.toDTO() ?: return ShopReportDTO(0, 0.0, 0.0)
    }
}