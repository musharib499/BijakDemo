package com.innobles.bijakmusharib.networkcall.module


import com.google.gson.annotations.SerializedName

data class NewsFeedResponse(
    @SerializedName("articles")
    var articles: List<Article?>,
    @SerializedName("status")
    var status: String?, // ok
    @SerializedName("totalResults")
    var totalResults: Int?, // 38
    @SerializedName("message")
    var message: String?,
) {
    data class Article(
        @SerializedName("author")
        var author: String?, // https://www.facebook.com/bbcnews
        @SerializedName("content")
        var content: String?, // media captionHighlights of Sir Sean Connery's acting careerSir Sean Connery has died at the age of 90, his family has said.The Scottish actor was best known for his portrayal of James Bond, being… [+5764 chars]
        @SerializedName("description")
        var description: String?, // He was the first to bring James Bond to the big screen and played the role seven times.
        @SerializedName("publishedAt")
        var publishedAt: String?, // 2020-10-31T14:21:00Z
        @SerializedName("source")
        var source: Source?,
        @SerializedName("title")
        var title: String?, // Sean Connery: James Bond actor dies aged 90 - BBC News
        @SerializedName("url")
        var url: String?, // https://www.bbc.com/news/entertainment-arts-54761824
        @SerializedName("urlToImage")
        var urlToImage: String? // https://ichef.bbci.co.uk/news/1024/branded_news/16A2B/production/_115151729_seancreuters.jpg
    ) {
        data class Source(
            @SerializedName("id")
            var id: Any?, // null
            @SerializedName("name")
            var name: String? // BBC News
        )
    }
}