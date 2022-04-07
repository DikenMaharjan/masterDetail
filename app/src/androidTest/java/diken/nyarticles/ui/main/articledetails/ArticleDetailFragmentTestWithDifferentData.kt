package diken.nyarticles.ui.main.articledetails

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.gson.Gson
import com.google.gson.JsonArray
import diken.nyarticles.R
import diken.nyarticles.network.response.viewedarticleresponse.Article
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Before
import org.junit.Test

class ArticleDetailFragmentTestWithDifferentData {

    lateinit var articleJson: JSONObject

    @Before
    fun init() {
        articleJson = JSONObject(
            SampleResponse.response
        )
    }

    @Test
    fun testNoPhotoFoundIsDisplayedOnNoUrl() {
        val firstItem = articleJson.getJSONArray("results").getJSONObject(0)
        firstItem.remove("media")
        firstItem.put("media", JSONArray())
        launchFragmentInContainer<ArticleDetailFragment>(
            ArticleDetailFragmentArgs(
                Gson().fromJson(firstItem.toString(), Article::class.java)
            ).toBundle(),
            themeResId = R.style.Theme_NyArticles
        )
        Espresso.onView(ViewMatchers.withId(R.id.articlesDetailFrg_noPhotosFound_TV)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
    }

}