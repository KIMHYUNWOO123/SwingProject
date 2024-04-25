package com.example.swingproject.di

import android.content.Context
import androidx.room.Room
import com.example.data.ApiService
import com.example.data.db.PhotoDB
import com.example.swingproject.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://api.unsplash.com/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return OkHttpClient.Builder().run {
            connectTimeout(30_000, TimeUnit.MILLISECONDS)
            readTimeout(30_000, TimeUnit.MILLISECONDS)
            addInterceptor(interceptor)
            build()
        }
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("baseUrl") baseUrl: String, gson: Gson, client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhotoDB = Room.databaseBuilder(
        context.applicationContext, PhotoDB::class.java, "PhotoDB"
    ).fallbackToDestructiveMigration().build()
}