package com.anthonychaufrias.test1.di

import com.anthonychaufrias.test1.data.network.CountryAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ticsolu.com/mvvm/api/v.1.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryAPI(retrofit: Retrofit): CountryAPIClient {
        return retrofit.create(CountryAPIClient::class.java)
    }

}
