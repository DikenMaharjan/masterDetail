package diken.nyarticles.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import diken.nyarticles.network.apiservices.ArticleApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(getLoggingInterceptor())
        }.build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun providesArticlesApi(retrofit: Retrofit) = retrofit.create(ArticleApi::class.java)


    companion object {
        const val BASE_URL = "https://api.nytimes.com/"
    }
}