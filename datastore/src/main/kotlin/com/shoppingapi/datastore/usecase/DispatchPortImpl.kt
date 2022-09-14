package com.shoppingapi.datastore.usecase

import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch
import com.shoppingapi.datastore.dataprovider.repository.dynamodb.port.DispatchRepository
import com.shoppingapi.datastore.usecase.port.DispatchPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DispatchPortImpl: DispatchPort {

    @Autowired
    private lateinit var dispatchRepository: DispatchRepository

    override fun findById(partKey: String): Dispatch {
        return dispatchRepository.findById(partKey)
    }

    override fun findAll(partKey: String, sortKey: String): List<Dispatch> {
        return dispatchRepository.findAll(partKey, sortKey)
    }

    override fun save(dispatch: Dispatch): Dispatch {
        return dispatchRepository.save(dispatch)
    }

}