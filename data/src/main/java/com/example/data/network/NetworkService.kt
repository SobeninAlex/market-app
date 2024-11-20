package com.example.data.network

import com.example.data.dto.request.AddProductToCartRequest
import com.example.data.dto.response.CartListResponse
import com.example.data.dto.response.CartSummaryResponse
import com.example.data.dto.response.CategoryListResponse
import com.example.data.dto.response.ProductListResponse
import com.example.domain.Cart
import com.example.domain.Category
import com.example.domain.Product
import com.example.domain.Summary
import com.example.utils.helper.NetworkResultWrapper
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

    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/v2"

    suspend fun getProducts(category: Int?): NetworkResultWrapper<List<Product>> {
        val url =
            if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { products: ProductListResponse ->
                products.data.map { it.toProduct() }
            }
        )
    }

    suspend fun getCategories(): NetworkResultWrapper<List<Category>> {
        val url = "$baseUrl/categories"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { categories: CategoryListResponse ->
                categories.data.map { it.toCategory() }
            }
        )
    }

    suspend fun addProductToCart(request: AddProductToCartRequest): NetworkResultWrapper<List<Cart>> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Post,
            body = request,
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.data.map { it.toCart() }
            }
        )
    }

    suspend fun getCart(): NetworkResultWrapper<List<Cart>> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.data.map { it.toCart() }
            }
        )
    }

    suspend fun updateQuantity(
        request: AddProductToCartRequest,
        cartId: Int
    ): NetworkResultWrapper<List<Cart>> {
        val url = "$baseUrl/cart/1/${cartId}"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Put,
            body = request,
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.data.map { it.toCart() }
            }
        )
    }

    suspend fun removeItem(cartId: Int, userId: Int): NetworkResultWrapper<List<Cart>> {
        val url = "$baseUrl/cart/$userId/$cartId"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Delete,
            mapper = { cartListResponse: CartListResponse ->
                cartListResponse.data.map { it.toCart() }
            }
        )
    }

    suspend fun getCartSummary(userId: Int): NetworkResultWrapper<Summary> {
        val url = "$baseUrl/checkout/$userId/summary"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { response: CartSummaryResponse ->
                response.data.toSummaryData()
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
    ): NetworkResultWrapper<O> {
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
            NetworkResultWrapper.Success(result)
        } catch (e: Exception) {
            NetworkResultWrapper.Failure(e)
        }
    }

}