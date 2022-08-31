package com.shoppingapi.datastore.dataprovider.report.aggregate

import java.math.BigDecimal

interface ShopReportAggregate{

    fun getCount(): Long?
    fun getTotal(): BigDecimal?
    fun getMean(): Double?

}
