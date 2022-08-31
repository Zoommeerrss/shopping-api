package com.shoppingapi.app.exception.system

import com.shoppingapi.app.exception.business.OrderNotFoundException
import com.shoppingapi.app.exception.business.ProductNotFoundException
import com.shoppingapi.app.exception.business.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.util.function.Consumer


//@ControllerAdvice(basePackages = ["com.shoppingapi.app.entrypoint"])
@RestControllerAdvice
class ShopControllerAdvice {

    @ExceptionHandler
    fun handleOrderNotFoundException(exception: OrderNotFoundException, request: WebRequest): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getErrorMessage())
    }

    @ExceptionHandler
    fun handleProductNotFoundException(exception: ProductNotFoundException, request: WebRequest): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getErrorMessage())
    }

    @ExceptionHandler
    fun handleUserNotFoundException(exception: UserNotFoundException, request: WebRequest): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getErrorMessage())
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String?>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(OrderNotFoundException::class)
//    fun handleUserNotFound(orderNotFoundException: OrderNotFoundException?): ErrorDTO? {
//
//        return ErrorDTO(
//            status = HttpStatus.NOT_FOUND.value(),
//            message = "Order not found.",
//            timestamp = Date()
//        )
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun processValidationError(ex: MethodArgumentNotValidException): ErrorDTO? {
//
//        val result = ex.bindingResult
//        val fieldErrors = result.fieldErrors
//        val sb = StringBuilder("Invalid value for the following fields: ")
//
//        for (fieldError in fieldErrors) {
//            sb.append(" ")
//            sb.append(fieldError.field)
//        }
//
//        return ErrorDTO(
//            status = HttpStatus.BAD_REQUEST.value(),
//            message = sb.toString(),
//            timestamp = Date()
//        )
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NoSuchElementException::class)
//    fun handleNoSuchElementException(ex:NoSuchElementException): ErrorDTO? {
//
//        return ErrorDTO(
//            status = HttpStatus.BAD_REQUEST.value(),
//            message = ex.message.toString(),
//            timestamp = Date()
//        )
//    }
}