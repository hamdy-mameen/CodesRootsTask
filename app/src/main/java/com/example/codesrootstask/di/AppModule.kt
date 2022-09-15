package com.example.codesrootstask.di

import android.view.View
import com.example.codesrootstask.Constants
import com.example.codesrootstask.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        try {

                // Create a trust manager that does not validate certificate chains
                val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?){}
                    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate>  = arrayOf()
                })
            // Install the all-trusting trust manager
            val  sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
                okHttpClient.hostnameVerifier { _, _ -> true }

            }
            return okHttpClient.addInterceptor(loggingInterceptor).build()
        }catch (e:Exception){
            return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .build()
        }

    }
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL


     @Provides
     @Singleton
    fun provideRetrofitInstance(client: OkHttpClient,baseUrl:String) =
           Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl(baseUrl)
               .client(client)
               .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

}