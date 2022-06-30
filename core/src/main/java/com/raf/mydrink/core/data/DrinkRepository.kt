package com.raf.mydrink.core.data

import com.raf.mydrink.core.data.source.local.LocalDataSource
import com.raf.mydrink.core.data.source.remote.RemoteDataSource
import com.raf.mydrink.core.data.source.remote.network.ApiResponse
import com.raf.mydrink.core.data.source.remote.response.DrinkResponse
import com.raf.mydrink.core.domain.model.Drink
import com.raf.mydrink.core.domain.repository.IDrinkRepository
import com.raf.mydrink.core.utils.AppExecutors
import com.raf.mydrink.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrinkRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDrinkRepository {

    override fun getAllDrink(): Flow<Resource<List<Drink>>> =
        object : com.raf.mydrink.core.data.NetworkBoundResource<List<Drink>, List<DrinkResponse>>() {
            override fun loadFromDB(): Flow<List<Drink>> {
                return localDataSource.getAllDrink().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Drink>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DrinkResponse>>> =
                remoteDataSource.getAllDrink()

            override suspend fun saveCallResult(data: List<DrinkResponse>) {
                val drinkList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertDrink(drinkList)
            }
        }.asFlow()

    override fun getFavoriteDrink(): Flow<List<Drink>> {
        return localDataSource.getFavoriteDrink().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteDrink(drink: Drink, state: Boolean) {
        val drinkEntity = DataMapper.mapDomainToEntity(drink)
        appExecutors.diskIO().execute { localDataSource.setFavoriteDrink(drinkEntity, state) }
    }

}