package com.raf.mydrink.core.di

import androidx.room.Room
import com.raf.mydrink.core.data.DrinkRepository
import com.raf.mydrink.core.data.source.local.LocalDataSource
import com.raf.mydrink.core.data.source.local.room.DrinkDatabase
import com.raf.mydrink.core.data.source.remote.RemoteDataSource
import com.raf.mydrink.core.data.source.remote.network.ApiService
import com.raf.mydrink.core.domain.repository.IDrinkRepository
import com.raf.mydrink.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DrinkDatabase>().drinkDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DrinkDatabase::class.java, "Drink.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IDrinkRepository> { DrinkRepository(get(), get(), get()) }
}