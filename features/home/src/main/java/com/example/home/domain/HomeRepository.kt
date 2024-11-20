package com.example.home.domain

import com.example.domain.Category
import com.example.domain.Product
import com.example.utils.helper.NetworkResultWrapper

interface HomeRepository {

    suspend fun getProducts(category: Int?): NetworkResultWrapper<List<Product>>

    suspend fun getCategories(): NetworkResultWrapper<List<Category>>

}