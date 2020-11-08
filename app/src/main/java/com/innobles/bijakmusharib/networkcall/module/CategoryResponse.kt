package com.innobles.bijakmusharib.networkcall.module


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("category")
    var category: List<Category>
) {
    data class Category(
        @SerializedName("key")
        var key: String?, // business
        @SerializedName("value")
        var value: String?, // Business
        var isCheck: Boolean = false
    )
}