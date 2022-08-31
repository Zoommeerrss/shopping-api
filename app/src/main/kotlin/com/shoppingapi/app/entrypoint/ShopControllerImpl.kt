package com.shoppingapi.app.entrypoint

import com.shoppingapi.app.entity.api.request.ShopRequest
import com.shoppingapi.app.entity.api.response.ShopResponse
import com.shoppingapi.app.entity.report.response.ShopReportResponse
import com.shoppingapi.app.exception.business.OrderNotFoundException
import com.shoppingapi.app.mapper.toDTO
import com.shoppingapi.app.mapper.toResponse
import com.shoppingapi.domain.entity.dto.ShopDTO
import com.shoppingapi.domain.usecase.port.ReportServicePort
import com.shoppingapi.domain.usecase.port.ShopServicePort
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/orders")
@Validated
class ShopControllerImpl {

    @Autowired
    private lateinit var servicePort: ShopServicePort

    @Autowired
    private lateinit var repostPort: ReportServicePort

    @Operation(summary = "Gera Relatorio de Vendas", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation", content = arrayOf(
                Content(
                    schema = Schema(
                        implementation = ShopReportResponse::class
                    )
                )
            )),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/buildReportByDate"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun buildReportByDate(
        @RequestParam(name = "dataInicio", required=true)
        @DateTimeFormat(pattern="yyyy-MM-dd")
        dataInicio: Date,
        @RequestParam(name = "dataFim", required=true)
        @DateTimeFormat(pattern="yyyy-MM-dd")
        dataFim: Date): ResponseEntity<ShopReportResponse> {

        val result = repostPort.getReportByDate(dataInicio, dataFim)

        if(result == null)
            throw OrderNotFoundException(404, "Report is empty.")

        return ResponseEntity(result.toResponse(), HttpStatus.OK)
    }

    @Operation(summary = "Lista compras com paginação", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findAll"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(
        @RequestParam(name = "pageNo", defaultValue = "0") pageNo: Int,
        @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int,
        @RequestParam(name = "sortBy", defaultValue = "id") sortBy: String): ResponseEntity<List<ShopResponse>> {

        val list = servicePort.findAll(pageNo, pageSize, sortBy)
            .stream()
            .map(ShopDTO::toResponse)
            .collect(Collectors.toList())

        if (list.isEmpty())
            throw OrderNotFoundException(404, "Empty")

        return ResponseEntity(list, HttpStatus.OK)
    }

    @Operation(summary = "Busca compras por id", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(name = "id", required = true) id: Long): ResponseEntity<ShopResponse> {

        val shop = servicePort.findById(id)

        if (!shop.isPresent)
            throw OrderNotFoundException(404, "Empty")

        return ResponseEntity(shop.get().toResponse(), HttpStatus.OK)
    }

    @Operation(summary = "Grava compra", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(
        @Valid
        @RequestBody(required = true)
        shop: ShopRequest): ResponseEntity<ShopResponse> {
        return ResponseEntity(servicePort.save(shop.toDTO()).toResponse(), HttpStatus.OK)
    }

    @Operation(summary = "Remove compra", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteById(@PathVariable(name = "id", required = true) id: Long): ResponseEntity<Long> {

        val shop = servicePort.countUserById(id)

        if(shop.toInt() == 0)
            throw OrderNotFoundException(404, "Empty")

        servicePort.deleteById(id)

        return ResponseEntity(shop, HttpStatus.OK)
    }

    @Operation(summary = "Lista compras por usuario", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findAllByUserIdentifier/{userIdentifier}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllByUserIdentifier(@PathVariable(name = "userIdentifier", required = true) userIdentifier: String): ResponseEntity<List<ShopResponse>> {

        val list = servicePort.findAllByUserIdentifier(userIdentifier)
            .stream()
            .map(ShopDTO::toResponse)
            .collect(Collectors.toList())

        if(list.isEmpty())
            throw OrderNotFoundException(404, "Empty")

        return ResponseEntity(list, HttpStatus.OK)
    }

    @Operation(summary = "Lista compras por maior valor", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findAllByTotalGreaterThan/{total}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllByTotalGreaterThan(@PathVariable(name = "total", required = true) total: BigDecimal): ResponseEntity<List<ShopResponse>> {

        val list = servicePort.findAllByTotalGreaterThan(total)
            .stream()
            .map(ShopDTO::toResponse)
            .collect(Collectors.toList())

        if(list.isEmpty())
            throw OrderNotFoundException(404, "Empty")

        return ResponseEntity(list, HttpStatus.OK)
    }

    @Operation(summary = "Lista compras pela maior data", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findAllByDateCreatedGreaterThanEquals/{createDate}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllByDateCreatedGreaterThanEquals(
        @PathVariable("createDate", required = true)
        @DateTimeFormat(pattern="yyyy-MM-dd")
        createDate: Date): ResponseEntity<List<ShopResponse>> {

        val list = servicePort.findAllByDateCreatedGreaterThanEquals(createDate)
            .stream()
            .map(ShopDTO::toResponse)
            .collect(Collectors.toList())

        if(list.isEmpty())
            throw OrderNotFoundException(404, "Empty")

        return ResponseEntity(list, HttpStatus.OK)
    }
}