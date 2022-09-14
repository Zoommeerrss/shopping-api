package com.shoppingapi.datastore.usecase.port

import com.shoppingapi.core.command.DispatchCommand
import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch

interface DispatchPort: DispatchCommand<Dispatch> {

    override fun findById(partKey: String): Dispatch
    override fun findAll(partKey: String, sortKey: String): List<Dispatch>
    override fun save(dispatch: Dispatch): Dispatch
}