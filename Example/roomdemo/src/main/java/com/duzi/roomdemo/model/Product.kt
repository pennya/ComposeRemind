package com.duzi.roomdemo.model

// DTO (Data Transfer Object)
// UI Layer 에서 사용하는 데이터 클래스
data class Product(
    val id: Int = 0,
    val productName: String = "",
    val quantity: Int = 0
)
