package com.duzi.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    val id: Int = 0,

    @ColumnInfo(name = "productName")
    val productName: String = "",
    val quantity: Int = 0
)