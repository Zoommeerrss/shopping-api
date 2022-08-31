package com.shoppingapi.datastore.dataprovider.entity.postgres

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Item(

    @Column(name = "price", nullable = false)
    val price: BigDecimal,

    @Column(name = "productIdentifier", nullable = false)
    val productIdentifier: String,
): Serializable
