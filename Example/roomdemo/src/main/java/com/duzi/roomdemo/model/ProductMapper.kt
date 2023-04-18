package com.duzi.roomdemo.model

fun ProductEntity.toDto(): Product {
    return Product(
        id = id,
        productName = productName,
        quantity = quantity
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        productName = productName,
        quantity = quantity
    )
}