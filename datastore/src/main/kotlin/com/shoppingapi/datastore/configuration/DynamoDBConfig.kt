package com.shoppingapi.datastore.configuration

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = arrayOf("com.shoppingapi.datastore.dataprovider.repository.dynamodb"))
class DynamoDBConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    var endpoint: String? = null

    @Value("\${amazon.aws.accesskey}")
    var accesskey: String? = null

    @Value("\${amazon.aws.secretkey}")
    var secretkey: String? = null

    @Value("\${amazon.aws.region}")
    var region: String? = null

    fun endpointConfiguration(): EndpointConfiguration? {
        return EndpointConfiguration(endpoint, region)
    }

    fun awsCredentialsProvider(): AWSCredentialsProvider? {
        return AWSStaticCredentialsProvider(BasicAWSCredentials(accesskey, secretkey))
    }

    @Bean
    fun dynamoDBMapper(): DynamoDBMapper? {
        return DynamoDBMapper(amazonDynamoDB())
    }

    private fun amazonDynamoDB(): AmazonDynamoDB? {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(endpointConfiguration())
            .withCredentials(awsCredentialsProvider())
            .build()
    }

}