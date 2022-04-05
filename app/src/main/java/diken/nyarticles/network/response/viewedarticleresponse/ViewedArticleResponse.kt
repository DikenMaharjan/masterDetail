package diken.nyarticles.network.response.viewedarticleresponse

data class ViewedArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Article>,
    val status: String
)