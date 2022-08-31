package com.shoppingapi.datastore.dataprovider.entity.postgres

import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "shop")
data class Shop(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long,

    @Column(name = "userIdentifier", nullable = false)
    var userIdentifier: String,

    @Column(name = "total", nullable = false)
    val total: BigDecimal,

    @CreationTimestamp
    @Column(name = "createDate", updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val createDate: Date,

    @ElementCollection(fetch = FetchType.LAZY, targetClass = Item::class)
    @CollectionTable(
        name = "item",
        joinColumns = [JoinColumn(name = "shop_id")],
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    val items: List<Item>
): Serializable
