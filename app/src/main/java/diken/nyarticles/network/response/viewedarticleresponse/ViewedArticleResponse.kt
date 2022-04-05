package diken.nyarticles.network.response.viewedarticleresponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ViewedArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Article>,
    val status: String
) : Parcelable