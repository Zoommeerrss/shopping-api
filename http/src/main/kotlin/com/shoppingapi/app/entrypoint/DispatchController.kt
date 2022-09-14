package com.shoppingapi.app.entrypoint

import com.shoppingapi.core.api.entity.request.DispatchRequest
import com.shoppingapi.core.api.entity.api.response.DispatchResponse
import com.shoppingapi.app.exception.business.DispathNotFoundException
import com.shoppingapi.core.api.mapper.toDTO
import com.shoppingapi.core.api.mapper.toResponse
import com.shoppingapi.core.domain.entity.DispatchDTO
import com.shoppingapi.domain.usecase.port.DispatchServicePort
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/dispatches")
@Validated
class DispatchController {

    @Autowired
    private lateinit var dispatchServicePort: DispatchServicePort

    @Operation(summary = "Lista entregas e seus status", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation", content = arrayOf(
                Content(
                    schema = Schema(
                        implementation = DispatchResponse::class
                    )
                )
            )),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun list(
        @RequestParam(name = "user_id", required=true)
        user_id: String,
        @RequestParam(name = "order_id", required=true)
        order_id: String
    ): ResponseEntity<List<DispatchResponse>> {

        val result = dispatchServicePort.findAll(user_id, order_id)
            .stream()
            .map(DispatchDTO::toResponse)
            .collect(Collectors.toList()) ?: throw DispathNotFoundException(404, "Report is empty.")

        return ResponseEntity(result, HttpStatus.OK)
    }

    @Operation(summary = "Gravar uma nova solicitação de entrega", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation", content = arrayOf(
                Content(
                    schema = Schema(
                        implementation = DispatchRequest::class
                    )
                )
            )),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @PostMapping(value = ["/"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(
        @RequestBody(required = true)
        @Valid
        dispatchRequest: DispatchRequest
    ): ResponseEntity<DispatchResponse> {

        val result = dispatchServicePort.save(dispatchRequest.toDTO()).toResponse()

        return ResponseEntity(result, HttpStatus.OK)
    }
}