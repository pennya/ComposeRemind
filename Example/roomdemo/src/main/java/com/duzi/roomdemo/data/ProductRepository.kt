package com.duzi.roomdemo.data

import com.duzi.roomdemo.model.Product
import com.duzi.roomdemo.model.ProductDao
import com.duzi.roomdemo.model.toDto
import com.duzi.roomdemo.model.toEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// TODO hilt migration with interface + datasource
class ProductRepository(private val productDao: ProductDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(product: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.insertProduct(product.toEntity())
        }
    }

    fun findProduct(name: String): List<Product> {
        return runBlocking(Dispatchers.IO) {
            // search delay
            delay(10000)
            println("#3 current thread: ${Thread.currentThread().name}")
            productDao.findProduct(name).map {
                it.toDto()
            }
        }
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProductsFlow().map { entities ->
            entities.map { it.toDto() }
        }
    }
}