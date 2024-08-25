package com.sonusourav.atlys.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sonusourav.atlys.BuildConfig
import com.sonusourav.atlys.data.api.ApiService
import com.sonusourav.atlys.data.repository.MovieRepository
import com.sonusourav.atlys.domain.usecases.UseCases
import com.sonusourav.atlys.domain.usecases.MovieDetailsUseCase
import com.sonusourav.atlys.domain.usecases.SearchMoviesUsecase
import com.sonusourav.atlys.domain.usecases.TrendingMoviesUseCase
import com.sonusourav.atlys.utils.Constants.BASE_API_URL
import com.sonusourav.atlys.utils.Constants.CONNECT_TIMEOUT
import com.sonusourav.atlys.utils.Constants.READ_TIMEOUT
import com.sonusourav.atlys.utils.Constants.WRITE_TIMEOUT
import okhttp3.Interceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun clientInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_API_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun useCases(repository: MovieRepository): UseCases = UseCases(
        TrendingMoviesUseCase(repository),
        MovieDetailsUseCase(repository),
        SearchMoviesUsecase(repository),
    )
}