package com.duzi.roomdemo.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duzi.roomdemo.data.ProductRepository
import com.duzi.roomdemo.model.Product
import com.duzi.roomdemo.model.ProductRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// TODO hilt migration
class MainViewModel(application: Application): ViewModel() {

    private val repository: ProductRepository
    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()

    private val _searchResults: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val searchResults: StateFlow<List<Product>>
        get() = _searchResults.asStateFlow()

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

    fun findProduct(name: String) {
        println("#1 current thread: ${Thread.currentThread().name}")
        viewModelScope.launch(Dispatchers.IO) {
            println("#2 current thread: ${Thread.currentThread().name}")
            val results = repository.findProduct(name)
            _searchResults.value = results
        }
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }
}