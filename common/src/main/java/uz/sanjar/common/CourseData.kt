package uz.sanjar.common

import com.google.gson.annotations.SerializedName

data class CourseData(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("rate") var rate: String? = null,
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("hasLike") var hasLike: Boolean? = null,
    @SerializedName("publishDate") var publishDate: String? = null,
)

fun CourseData.toCourseEntity(): CourseEntity = CourseEntity(
    id = id ?: -1,
    title = title ?: "",
    text = text ?: "",
    price = price ?: "",
    rate = rate ?: "",
    startDate = startDate ?: "",
    publishDate = publishDate ?: ""
)