package ua.com.tvv1n.weather.network.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    fun <S> getRequest(baseUrl: String, serviceClass: Class<S>?) :S {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
         return retrofit.create(serviceClass!!)
    }
    companion object {
        fun getInstance() = RetrofitClient()
    }
}
