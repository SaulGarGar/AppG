package com.garcia.saul.appg.data.api.retrofit

import com.garcia.saul.appg.BuildConfig
import com.garcia.saul.appg.data.api.client.GrinService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitClient {

    private val retrofit: Retrofit = retrofitBuilder()
    private var grinService = retrofit.create(getGrinServiceClass())

    private fun retrofitBuilder(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getGrinServiceClass(): Class<GrinService>{

        return GrinService::class.java
    }

    fun getGrinService(): GrinService {
        return grinService
    }

}