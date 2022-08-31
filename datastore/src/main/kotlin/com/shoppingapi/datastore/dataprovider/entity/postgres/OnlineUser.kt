package com.shoppingapi.datastore.dataprovider.entity.postgres

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "online_user")
@JsonIgnoreProperties(
    ignoreUnknown = true
)
data class OnlineUser(

    @Id
    @Column(name = "cpf", nullable = false)
    val cpf: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "address", nullable = false)
    val address: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "phone", nullable = false)
    val phone: String,

    @CreationTimestamp
    @Column(name = "createDate", updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val createDate: Date,
): Serializable
