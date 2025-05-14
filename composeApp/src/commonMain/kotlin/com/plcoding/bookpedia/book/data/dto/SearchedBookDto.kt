package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("key") val id: String,
    @SerialName("title") val title: String,
    @SerialName("languages") val language: List<String>? = null,
    @SerialName("cover_i") val coverAlternateKey: Int? = null,
    @SerialName("author_key") val authors: List<String>? = null,
    @SerialName("author_name") val authorNames: List<String>? = null,
    @SerialName("cover_edition_key") val coverKey: Int? = null,
    @SerialName("first_publish_year") val firstPublishYear: String? = null,
    @SerialName("ratings_average") val ratingsAverage: Double? = null,
    @SerialName("ratings_count") val ratingsCount: Double? = null,
    @SerialName("edition_count") val numEditions: Int? = null,
    @SerialName("num_page_medium") val numPageMedian: Double? = null,
)
