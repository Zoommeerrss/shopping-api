package com.shoppingapi.core.api.entity.enum

enum class MessageEnum(private val code: String, private val message: String) {

    E000("000", "Order processed succesfully."),
    E001("001", "User processed succesfully."),
    E002("002", "Item processed succesfully.");

    fun formattedMessage(): String {
        return "${this.code.toString()}:${this.message.toString()}"
    }

}