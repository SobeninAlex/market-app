package com.example.domain.repository

import com.example.domain.entity.CategoryList
import com.example.domain.entity.Product
import com.example.domain.network.ResultWrapper

interface CategoryRepository {

    suspend fun getCategories(): ResultWrapper<CategoryList>

}