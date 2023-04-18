package com.duzi.roomdemo.data

import com.duzi.roomdemo.model.Product
import com.duzi.roomdemo.model.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// TODO hilt migration with interface + datasource
class ProductRepository(private val productDao: ProductDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(product: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.insertProduct(product)
        }
    }

    fun findProduct(name: String): List<Product> {
        return runBlocking(Dispatchers.IO) {
            productDao.findProduct(name)
        }
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }
}