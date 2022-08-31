package com.shoppingapi.datastore.dataprovider.repository.dynamodb

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome
import com.amazonaws.services.dynamodbv2.document.Table
import com.shoppingapi.datastore.dataprovider.entity.dynamodb.Invoice
import com.shoppingapi.datastore.dataprovider.repository.dynamodb.port.InvoiceRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class InvoiceRepositoryImpl: InvoiceRepository {
    override fun findById(id: String?): Optional<Invoice?>? {

        val client = AmazonDynamoDBClientBuilder.standard().build()
        val dynamoDB = DynamoDB(client)

        val table: Table = dynamoDB.getTable("Invoice")
        val outcome: PutItemOutcome = table.putItem(Item())

        return null
    }

    override fun findAll(): List<Invoice?>? {

        val client = AmazonDynamoDBClientBuilder.standard().build()
        val dynamoDB = DynamoDB(client)

        val table: Table = dynamoDB.getTable("Invoice")
        val outcome: PutItemOutcome = table.putItem(Item())

        return null
    }
}