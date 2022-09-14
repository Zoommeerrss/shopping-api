package com.shoppingapi.datastore.dataprovider.repository.dynamodb

import com.shoppingapi.core.datastore.entity.dynamodb.Dispatch
import com.shoppingapi.datastore.dataprovider.repository.dynamodb.port.DispatchRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class DispatchRepositoryImplTest {

    @RelaxedMockK
    private lateinit var repository: DispatchRepository

    @Before(value = "Before start")
    fun setup() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `deve salvar um item na tabela Dispatch do DynamoDB`() {

        val dispatch = Dispatch(
            userId = "test",
            orderId = "1234",
            shipStatus = "true",
            lastUpdate = "2022-09-03"
        )

        every { repository.save(dispatch) } returns dispatch

        val result = repository.save(dispatch)

        verify(exactly = 1) { repository.save(dispatch)  }

        assertNotNull(result)
    }

    @Test
    fun `deve buscar um item na tabela Dispatch do DynamoDB`() {

        every { repository.findById("1") } returns Dispatch()

        val result = repository.findById("1")

        verify(exactly = 1) { repository.findById("1")  }

        assertNotNull(result)
    }
}