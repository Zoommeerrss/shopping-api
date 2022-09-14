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
@Schema(name = "ErrorMessage")
data class ErrorMessage(

    @field:Schema(name = "status")
    val status: Int,

    @field:Schema(name = "message")
    val message: String,

    @field:Schema(name = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    val timestamp: Date,

    @field:Schema(name = "fields")
    val fields: MutableList<ErrorField>
)