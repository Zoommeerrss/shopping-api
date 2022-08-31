package com.shoppingapi.datastore.dataprovider.repository.postgres

import com.shoppingapi.datastore.dataprovider.entity.postgres.Shop
import com.shoppingapi.datastore.dataprovider.report.aggregate.ShopReportAggregate
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface ReportRepository: PagingAndSortingRepository<Shop, Long> {

    @Query("select o " +
            "from Shop o " +
            "where cast(o.createDate as date) >= :dataInicio and cast(o.createDate as date) <= :dataFim and o.total <= :valorMinimo")
    fun getShopByFilters(@Param("dataInicio") dataInicio: Date?, @Param("dataFim") dataFim: Date?, @Param("valorMinimo") valorMinimo: BigDecimal?): List<Shop>

    @Query("select count(o.id) as count, cast(sum(o.total) as decimal(10,2)) as total, cast(avg(o.total) as decimal(10,2)) as mean " +
            "from Shop o " +
            "where cast(o.createDate as date) >= :dataInicio and cast(o.createDate as date) <= :dataFim", nativeQuery = true)
    fun generateShopReportByCreateDateBetween(@Param("dataInicio") dataInicio: Date?, @Param("dataFim") dataFim: Date?): ShopReportAggregate?
}