package uz.sanjar.entity.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.sanjar.entity.source.remote.CourseApi
import javax.inject.Singleton

/**   Created by Sanjar Karimov 4:44 PM 3/15/2025   */

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @[Provides Singleton]
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @[Provides Singleton]
    fun provideApiClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    @[Provides Singleton]
    fun provideCourseApi(retrofit: Retrofit): CourseApi = retrofit.create()
}