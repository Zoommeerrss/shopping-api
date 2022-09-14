package com.shoppingapi.app

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.ComponentScan

private const val DATASTORE_SERVICE = "com.shoppingapi.datastore"
private const val DOMAIN_SERVICE = "com.shoppingapi.domain"
private const val APP_CONTROLLER = "com.shoppingapi.app"

@SpringBootApplication
@ComponentScan(basePackages = [DATASTORE_SERVICE, DOMAIN_SERVICE, APP_CONTROLLER])
@EnableAutoConfiguration(exclude=[ DataSourceAutoConfiguration::class])
@OpenAPIDefinition(
    info = Info(
        title = "Shopping API",
        version = "0.1",
        description = "Microsservico para tratar Shopping API",
        license = License(name = "Undertow", url = "http://www.emerzoom.com"),
        contact = Contact(url = "emerzoom@emerzoom.com", name = "Emerzoom", email = "emerzoom@emerzoom.com")
    )
)
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}