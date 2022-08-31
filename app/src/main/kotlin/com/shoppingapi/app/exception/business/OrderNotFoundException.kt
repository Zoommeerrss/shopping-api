package com.shoppingapi.app.exception.business

import com.shoppingapi.app.exception.system.ErrorField
import com.shoppingapi.app.exception.system.ErrorMessage
import java.util.*

class OrderNotFoundException(code: Int, message: String, fields: List<ErrorField>?) : RuntimeException() {

    private lateinit var errorMessage: ErrorMessage

    constructor(code: Int, message: String) : this(code, message, mutableListOf<ErrorField>()) {
        errorMessage = ErrorMessage(
            status = code,
            message = message,
            timestamp = Date(),
            mutableListOf<ErrorField>()
        )
    }

    constructor(code: Int, message: String, uniqueFields: ErrorField) : this(code, message) {
        errorMessage = ErrorMessage(
            status = code,
            message = message,
            timestamp = Date(),
            mutableListOf<ErrorField>(uniqueFields)
        )
    }

    fun getErrorMessage(): ErrorMessage = errorMessage
}