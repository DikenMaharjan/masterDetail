package diken.nyarticles.ui.main.articledetails

import androidx.annotation.IdRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.gson.Gson
import diken.nyarticles.R
import diken.nyarticles.network.response.viewedarticleresponse.ViewedArticleResponse
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test

class ArticleDetailFragmentTestWithSameData {


    private val sampleArticle =
        Gson().fromJson(SampleResponse.response, ViewedArticleResponse::class.java).results[0]

    @Before
    fun init() {
        launchFragmentInContainer<ArticleDetailFragment>(
            fragmentArgs = ArticleDetailFragmentArgs(
                sampleArticle
            ).toBundle(),
            themeResId = R.style.Theme_NyArticles
        )
    }

    @Test
    fun testTitleIsSetProperly() {
        testTextThereOrNot(R.id.articlesDetailFrg_title_TV, sampleArticle.title)
    }

    private fun testTextThereOrNot(@IdRes id: Int, text: String) {
        Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    @Test
    fun testAuthorIsSetProperly() {
        testTextThereOrNot(R.id.articlesDetailFrg_author_TV, sampleArticle.byline)
    }

    @Test
    fun testUrlIsSetProperly() {
        testTextThereOrNot(R.id.articleDetailFrg_url_TV, sampleArticle.url)
    }

    @Test
    fun noImageFoundIsNotVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.articlesDetailFrg_noPhotosFound_TV)).check(
            ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed()))
        )
    }
}