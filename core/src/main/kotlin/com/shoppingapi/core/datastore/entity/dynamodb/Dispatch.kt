package com.shoppingapi.core.datastore.entity.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.*

@DynamoDBTable(tableName = "Dispatch")
data class Dispatch(

    @DynamoDBHashKey(attributeName = "user_id")
    //@DynamoDBIndexRangeKey(globalSecondaryIndexName = "user_id_gsi", attributeName = "user_id")
    var userId: String? = null,

    @DynamoDBRangeKey(attributeName = "order_id")
    var orderId: String? = null,

    @DynamoDBAttribute(attributeName = "ship_status")
    var shipStatus: String? = null,

    @DynamoDBAttribute(attributeName = "last_update")
    var lastUpdate: String? = null,
)