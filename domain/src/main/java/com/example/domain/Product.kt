package com.example.domain

import android.os.Parcelable
import com.example.utils.helper.StringSanitizer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
    val categoryId: Int,
    @Serializable(StringSanitizer::class) val description: String,
    val id: Int,
    @Serializable(StringSanitizer::class) val image: String,
    val price: Double,
    @Serializable(StringSanitizer::class) val title: String
) : Parcelable {

    val priceString: String get() = "$$price"

    companion object {
        val DEFAULT = Product(
            categoryId = 1,
            description = "Description",
            id = 1,
            image = "https://s3-alpha-sig.figma.com/img/a081/7401/f0285c010bccdc79ea6b82c6a2de8997?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=UA4LKrZe3wH6drMxj-IfQ4WbwbuD79kGLcY5G7G2nZSbZfSqomQJndbqUrWg6sZoa4MAzWLCvjaEGGtRNgaDGD6M0-o0EqKwCiracbLbavfqyp0ZJmWKFCwd5cKTzjO75L1I~aECK6VKxLsgRANlbbD94KicjNlrFn5WSCV7Pp6IG0zosvZpFtrWUhrUzKNmprC3KVTPxdKglXv9mqVCJqvnyN4sTAvxxJY~rFGXc6q5O5nFPA~ZNH3Wvm6NQJi3cGy2~6THzTSU-P7vN~UVUriIta7X3pdh6qpNbwY3c33G7~xAeGkIHLth0j9QdBn6Ggou1m9bFD-BnYqO0Tt0qg__",
            price = 1.1,
            title = "Title Product"
        )
    }

}