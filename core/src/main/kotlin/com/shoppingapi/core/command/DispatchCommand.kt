package com.shoppingapi.core.command

import java.util.*

interface DispatchCommand<T> {

    fun findById(partKey: String): T
    fun findAll(partKey: String, sortKey: String): List<T>
    fun save(dispatch: T): T
}