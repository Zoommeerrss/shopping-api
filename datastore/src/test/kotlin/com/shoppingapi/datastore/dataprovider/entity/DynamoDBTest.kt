package com.shoppingapi.datastore.dataprovider.entity

import com.amazonaws.AmazonServiceException
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.*


class DynamoDBTest

    fun main(args: Array<String>) {
        val amazonDynamoDBClient = amazonDynamoDBClient
        val request = createTableRequest
        try {
            val result = amazonDynamoDBClient.createTable(request)
            println(result.tableDescription.tableName)
        } catch (e: AmazonServiceException) {
            System.err.println(e.errorMessage)
        }
    }

    //Create endpoint configuration which points to the Edge service (running on http://localhost:4566)
    private val amazonDynamoDBClient: AmazonDynamoDB
        get() {
            //Create endpoint configuration which points to the Edge service (running on http://localhost:4566)
            val endpointConfig = EndpointConfiguration(
                "http://localhost:4566",
                Regions.SA_EAST_1.getName()
            )
            return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConfig)
                .build()
        }

    private val createTableRequest: CreateTableRequest
        get() = CreateTableRequest()
            .withAttributeDefinitions(AttributeDefinition("Name", ScalarAttributeType.S))
            .withKeySchema(KeySchemaElement("Name", KeyType.HASH))
            .withProvisionedThroughput(ProvisionedThroughput(10L, 10L))
            .withTableName("MyTable")
