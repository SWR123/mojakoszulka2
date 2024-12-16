package com.example.mojakoszulka2.data.repository

import com.example.mojakoszulka2.data.OrderData
import com.example.mojakoszulka2.data.local.OrderDao
import com.example.mojakoszulka2.data.mapper.toEntity
import com.example.mojakoszulka2.data.mapper.toOrderData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) {
    fun getAllOrders(): Flow<List<OrderData>> =
        orderDao.getAllOrders().map { entities ->
            entities.map { it.toOrderData() }
        }

    suspend fun getOrderById(id: Long): OrderData? =
        orderDao.getOrderById(id)?.toOrderData()

    suspend fun saveOrder(order: OrderData): Long =
        orderDao.insertOrder(order.toEntity())

    suspend fun deleteOrder(order: OrderData) =
        orderDao.deleteOrder(order.toEntity())

    suspend fun deleteAllOrders() =
        orderDao.deleteAllOrders()
} 