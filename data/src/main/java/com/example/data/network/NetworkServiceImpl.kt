package com.example.data.network

import com.example.data.dto.response.CategoryListResponse
import com.example.data.dto.response.ProductListResponse
import com.example.domain.entity.CategoryList
import com.example.domain.entity.ProductList
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

class NetworkServiceImpl(
    val client: HttpClient
) : NetworkService {

    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com"

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        val url = if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"
        return makeWebRequest<ProductListResponse, ProductList>(
            url = url,
            method = HttpMethod.Get,
            mapper = { products: ProductListResponse ->
                products.toProductList()
            }
        )
    }

    override suspend fun getCategories(): ResultWrapper<CategoryList> {
        val url = "$baseUrl/categories"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { categories: CategoryListResponse ->
                categories.toCategoryList()
            }
        )
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified I, O> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((I) -> O)? = null
    ): ResultWrapper<O> {
        return try {
            val response = client.request(url) {
                this.method = method

                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }

                headers.forEach { (key, value) ->
                    header(key, value)
                }

                if (body != null) {
                    this.body = body
                }

                contentType(ContentType.Application.Json)
            }.body<I>()
            val result: O = mapper?.invoke(response) ?: response as O
            ResultWrapper.Success(result)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
    }

}