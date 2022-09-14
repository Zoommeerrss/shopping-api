package com.shoppingapi.core.datastore.entity.report.aggregate

import java.math.BigDecimal

interface ShopReportAggregate{

    fun getCount(): Long?
    fun getTotal(): BigDecimal?
    fun getMean(): Double?

}
