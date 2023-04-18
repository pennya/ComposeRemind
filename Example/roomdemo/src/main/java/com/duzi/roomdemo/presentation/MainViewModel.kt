package com.duzi.roomdemo.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duzi.roomdemo.data.ProductRepository
import com.duzi.roomdemo.model.Product
import com.duzi.roomdemo.model.ProductRoomDatabase
import kotlinx.coroutines.flow.*

// TODO hilt migration
class MainViewModel(application: Application): ViewModel() {

    private val repository: ProductRepository
    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()

    init {
        val database = ProductRoomDatabase.getInstance(application)
        val dao = database.productDao()
        repository = ProductRepository(dao)

        repository.getAllProducts()
            .onEach { products -> _products.value = products }
            .launchIn(viewModelScope)
    }

    fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun findProduct(name: String): List<Product> {
        return repository.findProduct(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }
}