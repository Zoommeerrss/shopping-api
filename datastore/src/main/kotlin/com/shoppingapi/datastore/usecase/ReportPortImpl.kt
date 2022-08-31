package com.shoppingapi.datastore.usecase

import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import com.shoppingapi.datastore.dataprovider.report.ShopReport
import com.shoppingapi.datastore.dataprovider.report.aggregate.ShopReportAggregate
import com.shoppingapi.datastore.dataprovider.repository.postgres.ReportRepository
import com.shoppingapi.datastore.usecase.port.ReportPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ReportPortImpl: ReportPort {

    @Autowired
    private lateinit var repository: ReportRepository

    override fun getShopByFilters(dataInicio: Date?, dataFim: Date?, valorMinimo: BigDecimal?): List<Shop> =
        repository.getShopByFilters(dataInicio, dataFim, valorMinimo)

    override fun getReportByDate(dataInicio: Date?, dataFim: Date?): ShopReport? {

        val report: ShopReportAggregate? = repository.generateShopReportByCreateDateBetween(dataInicio, dataFim)

        return if(report?.getTotal() == null || report.getMean() == null)
            ShopReport(0, 0.0, 0.0)
        else {
            ShopReport(report.getCount()!!, report.getTotal()!!.toDouble(), report.getMean()!!)
        }
    }
}