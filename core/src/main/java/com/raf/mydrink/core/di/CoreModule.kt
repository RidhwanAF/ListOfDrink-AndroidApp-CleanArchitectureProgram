package com.raf.mydrink.core.di

import androidx.room.Room
import com.raf.mydrink.core.data.DrinkRepository
import com.raf.mydrink.core.data.source.local.LocalDataSource
import com.raf.mydrink.core.data.source.local.room.DrinkDatabase
import com.raf.mydrink.core.data.source.remote.RemoteDataSource
import com.raf.mydrink.core.data.source.remote.network.ApiService
import com.raf.mydrink.core.domain.repository.IDrinkRepository
import com.raf.mydrink.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("raf".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            DrinkDatabase::class.java, "Drink.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "thecocktaildb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/B1WXiMHqYKI+lhU+U9xEAohJVSQjVO88WQvyCNxAmBU=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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