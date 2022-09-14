package com.shoppingapi.core.api.entity.request

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Schema(name = "UserRequest")
data class UserRequest(

    @field:NotBlank
    @field:Size(min = 10, max = 11)
    @field:Schema(name = "cpf")
    val cpf: String,

    @field:NotBlank
    @field:Schema(name = "name")
    val name: String,

    @field:NotBlank
    @field:Schema(name = "address")
    val address: String,

    @field:NotBlank
    @field:Email
    @field:Schema(name = "email")
    val email: String,

    @field:NotBlank
    @field:Schema(name = "phone")
    val phone: String,

    @field:Schema(name = "createDate")
    val createDate: Date,
)
