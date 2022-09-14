package com.shoppingapi.domain.usecase

import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch
import com.shoppingapi.datastore.dataprovider.repository.dynamodb.port.DispatchRepository
import com.shoppingapi.core.domain.entity.DispatchDTO
import com.shoppingapi.core.domain.mapper.toDTO
import com.shoppingapi.core.domain.mapper.toEntity
import com.shoppingapi.domain.usecase.port.DispatchServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class DispatchServicePortImpl: DispatchServicePort {

    @Autowired
    private lateinit var dispatchRepository: DispatchRepository

    override fun findById(partKey: String): DispatchDTO {
        return dispatchRepository.findById(partKey).toDTO()
    }

    override fun findAll(partKey: String, sortKey: String): List<DispatchDTO> {
        return dispatchRepository.findAll(partKey, sortKey)
            .stream()
            .map(Dispatch::toDTO)
            .collect(Collectors.toList())
    }

    override fun save(dispatch: DispatchDTO): DispatchDTO {
        return dispatchRepository.save(dispatch.toEntity()).toDTO()
    }

}