package com.shoppingapi.datastore.dataprovider.repository.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch
import com.shoppingapi.datastore.dataprovider.repository.dynamodb.port.DispatchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DispatchRepositoryImpl: DispatchRepository {

    @Autowired
    private lateinit var dynamoDBMapper: DynamoDBMapper

    override fun findById(partKey: String): Dispatch {

        return dynamoDBMapper.load(Dispatch::class.java, partKey)
    }

    override fun findAll(partKey: String, sortKey: String): List<Dispatch> {

        val eav: MutableMap<String, AttributeValue> = HashMap()
        eav[":v1"] = AttributeValue().withS(partKey)
        eav[":v2"] = AttributeValue().withS(sortKey)

        val queryExpression: DynamoDBQueryExpression<Dispatch> = DynamoDBQueryExpression<Dispatch>()
            .withKeyConditionExpression("user_id = :v1 and order_id = :v2")
            .withExpressionAttributeValues(eav)

        return dynamoDBMapper.query(Dispatch::class.java, queryExpression)
    }

    override fun save(dispatch: Dispatch): Dispatch {
        dynamoDBMapper.save(dispatch)
        return dispatch
    }
}