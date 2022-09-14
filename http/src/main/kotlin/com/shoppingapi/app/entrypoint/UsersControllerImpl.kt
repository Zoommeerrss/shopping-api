package com.shoppingapi.app.entrypoint

import com.shoppingapi.core.api.entity.request.UserRequest
import com.shoppingapi.core.api.entity.api.response.UserResponse
import com.shoppingapi.app.exception.business.UserNotFoundException
import com.shoppingapi.core.api.mapper.toDTO
import com.shoppingapi.core.api.mapper.toResponse
import com.shoppingapi.core.domain.entity.UserDTO
import com.shoppingapi.domain.usecase.port.UserServicePort
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
@RequestMapping("/users")
@Validated
class UsersControllerImpl {

    @Autowired
    private lateinit var servicePort: UserServicePort

    @Operation(summary = "Lista os usuários", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation", content = arrayOf(
                Content(
                    schema = Schema(
                        implementation = UserResponse::class
                    )
                )
            )),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/listTotal"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listTotal(): ResponseEntity<Long> {

        return ResponseEntity(servicePort.listTotal(), HttpStatus.OK)
    }

    @Operation(summary = "Lista os usuários com paginação", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findAll"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(@RequestParam(name = "pageNo", defaultValue = "0") pageNo: Int,
                         @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int,
                         @RequestParam(name = "sortBy", defaultValue = "name") sortBy: String): ResponseEntity<List<UserResponse>> {

        return ResponseEntity(servicePort.findAll(pageNo, pageSize, sortBy)
            .stream()
            .map(UserDTO::toResponse)
            .collect(Collectors.toList()),
            HttpStatus.OK)
    }

    @Operation(summary = "Lista os usuários em que o nome seja igual ao nome digitado", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findByNameLike/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByNameLike(@PathVariable(name = "name", required = true) name: String): ResponseEntity<List<UserResponse>> {
        return ResponseEntity(servicePort.findByNameLike(name)
            .stream()
            .map(UserDTO::toResponse)
            .collect(Collectors.toList()),
            HttpStatus.OK)
    }

    @Operation(summary = "Lista os usuários em que na extensao do nome contenha as letras digitadas", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/findByNameContaining/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByNameContaining(@PathVariable(name = "name", required = true) name: String): ResponseEntity<List<UserResponse>> {
        return ResponseEntity(servicePort.findByNameContaining(name)
            .stream()
            .map(UserDTO::toResponse)
            .collect(Collectors.toList()),
            HttpStatus.OK)
    }

    @Operation(summary = "Busca usuário por CPF", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @GetMapping(value = ["/{cpf}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(name = "cpf", required = true) cpf: String): ResponseEntity<UserResponse> {

        val shop = servicePort.findById(cpf)

        if (!shop.isPresent)
            throw UserNotFoundException(404, "Empty")

        return ResponseEntity(shop.get().toResponse(), HttpStatus.OK)
    }

    @Operation(summary = "Grava usuário", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@Valid
                      @RequestBody(required = true)
                      user: UserRequest): ResponseEntity<UserResponse> {

        return ResponseEntity(servicePort.save(user.toDTO()).toResponse(), HttpStatus.OK)
    }

    @Operation(summary = "Remove usuario", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "403", description = "Such a car does not exist"),
            ApiResponse(responseCode = "404", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal Server Error"),
        ]
    )
    @DeleteMapping(value = ["/{cpf}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteById(@PathVariable(name = "cpf") cpf: String): ResponseEntity<Long> {

        val shop = servicePort.countUserByCpf(cpf)

        if(shop.toInt() == 0)
            throw UserNotFoundException(404, "Empty")

        servicePort.deleteById(cpf)

        return ResponseEntity(shop, HttpStatus.OK)
    }
}