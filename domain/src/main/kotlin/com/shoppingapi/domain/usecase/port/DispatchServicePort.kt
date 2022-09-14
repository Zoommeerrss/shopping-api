package com.shoppingapi.domain.usecase.port

import com.shoppingapi.core.command.DispatchCommand
import com.shoppingapi.core.domain.entity.DispatchDTO

interface DispatchServicePort: DispatchCommand<DispatchDTO> {

    override fun findById(partKey: String): DispatchDTO
    override fun findAll(partKey: String, sortKey: String): List<DispatchDTO>
    override fun save(dispatch: DispatchDTO): DispatchDTO
}