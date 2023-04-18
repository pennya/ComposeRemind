package com.duzi.roomdemo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE productName = :name")
    suspend fun findProduct(name: String): List<ProductEntity>

    @Query("DELETE FROM products WHERE productName = :name")
    fun deleteProduct(name: String)

    @Query("SELECT * FROM products")
    fun getAllProductsFlow(): Flow<List<ProductEntity>>
}