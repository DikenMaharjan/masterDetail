package diken.nyarticles.network.response.viewedarticleresponse

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,

//    @SerializedName("media-metadata")
//    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)