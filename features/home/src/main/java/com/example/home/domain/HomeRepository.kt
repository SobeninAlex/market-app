package com.example.home.domain

import com.example.utils.domain.CategoryList
import com.example.utils.domain.ProductList
import com.example.utils.helper.ResultWrapper

interface HomeRepository {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductList>

    suspend fun getCategories(): ResultWrapper<CategoryList>

}