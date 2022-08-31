package com.shoppingapi.app.exception.system

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "ErrorField")
data class ErrorField(

    @field:Schema(name = "name")
    val name: String,

    @field:Schema(name = "message")
    val message: String,

    @field:Schema(name = "value")
    val value: String
)