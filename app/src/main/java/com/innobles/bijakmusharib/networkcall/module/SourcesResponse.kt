package com.innobles.bijakmusharib.networkcall.module


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class SourcesResponse(
    @SerializedName("sources")
    var sources: List<MySource?>,
    @SerializedName("status")
    var status: String? // ok
) {
    @Entity
    data class MySource(
        @PrimaryKey(autoGenerate = true)
        var mID: Int = 0,
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