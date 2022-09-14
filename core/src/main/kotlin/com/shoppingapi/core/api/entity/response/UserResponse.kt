package com.shoppingapi.core.api.entity.api.response

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
@Schema(name = "UserResponse")
data class UserResponse(
    @field:Schema(name = "cpf")
    val cpf: String,

    @field:Schema(name = "name")
    val name: String,

    @field:Schema(name = "address")
    val address: String,

    @field:Schema(name = "email")
    val email: String,

    @field:Schema(name = "phone")
    val phone: String,

    @field:Schema(name = "createDate")
    val createDate: Date,
)
