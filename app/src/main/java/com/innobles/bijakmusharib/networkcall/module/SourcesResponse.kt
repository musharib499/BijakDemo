package com.innobles.bijakmusharib.networkcall.module


import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @SerializedName("sources")
    var sources: List<Source?>,
    @SerializedName("status")
    var status: String? // ok
) {
    data class Source(
        @SerializedName("category")
        var category: String?, // general
        @SerializedName("country")
        var country: String?, // us
        @SerializedName("description")
        var description: String?, // Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.
        @SerializedName("id")
        var id: String?, // abc-news
        @SerializedName("language")
        var language: String?, // en
        @SerializedName("name")
        var name: String?, // ABC News
        @SerializedName("url")
        var url: String? // https://abcnews.go.com
    )
}