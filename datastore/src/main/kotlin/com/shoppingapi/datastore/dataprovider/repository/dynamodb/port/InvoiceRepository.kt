package com.shoppingapi.datastore.dataprovider.repository.dynamodb.port

import com.shoppingapi.datastore.dataprovider.entity.dynamodb.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import java.util.*

interface InvoiceRepository {

    fun findById(id: String?): Optional<Invoice?>?
    fun findAll(): List<Invoice?>?
}