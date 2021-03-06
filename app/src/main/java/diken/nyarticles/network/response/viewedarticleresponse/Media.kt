package diken.nyarticles.network.response.viewedarticleresponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,

    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
) : Parcelable