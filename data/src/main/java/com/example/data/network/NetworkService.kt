package com.example.data.network

import com.example.data.dto.request.AddCartRequestDto.Companion.fromAddCartRequest
import com.example.data.dto.response.CartListResponse
import com.example.data.dto.response.CategoryListResponse
import com.example.data.dto.response.ProductListResponse
import com.example.utils.domain.CartList
import com.example.utils.domain.CategoryList
import com.example.utils.domain.ProductList
import com.example.utils.domain.request.AddCartRequest
import com.example.utils.helper.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType

class NetworkService(
    val client: HttpClient
) {

    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com"

    suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        val url = if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { products: ProductListResponse ->
                products.toProductList()
            }
        )
    }

    suspend fun getCategories(): ResultWrapper<CategoryList> {
        val url = "$baseUrl/categories"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { categories: CategoryListResponse ->
                categories.toCategoryList()
            }
        )
    }

    suspend fun addProductToCart(request: AddCartRequest): ResultWrapper<CartList> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Post,
            body = fromAddCartRequest(request),
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.toCartList()
            }
        )
    }

    suspend fun getCart(): ResultWrapper<CartList> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.toCartList()
            }
        )
    }

    private suspend inline fun <reified I, O> makeWebRequest(
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
                    setBody(body)
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