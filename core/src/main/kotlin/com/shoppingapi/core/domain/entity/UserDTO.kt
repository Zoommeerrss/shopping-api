package com.shoppingapi.core.domain.entity

import java.util.*

data class UserDTO(

    val cpf: String,

    var name: String,

    val address: String,

    val email: String,

    val phone: String,

    val createDate: Date,
)
