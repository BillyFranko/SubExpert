package com.dicoding.subexpert1billy.core.data

import com.dicoding.subexpert1billy.core.data.source.local.LocalDataSource
import com.dicoding.subexpert1billy.core.data.source.remote.RemoteDataSource
import com.dicoding.subexpert1billy.core.data.source.remote.network.ApiResponse
import com.dicoding.subexpert1billy.core.data.source.remote.response.MealsItem
import com.dicoding.subexpert1billy.core.domain.model.Foods
import com.dicoding.subexpert1billy.core.domain.repository.IFoodsRepository
import com.dicoding.subexpert1billy.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IFoodsRepository{

    override fun getAllFoods(): Flow<Resource<List<Foods>>> =
        object : NetworkBoundResource<List<Foods>, List<MealsItem?>?>(){
        override fun loadFromDB(): Flow<List<Foods>> {
            return localDataSource.getAllFoods().map {
                DataMapper.mapEntitiesToDomain(it)
            }
        }

            override fun shouldFetch(data: List<Foods>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MealsItem?>?>> =
                remoteDataSource.getAllFoods()

            override suspend fun saveCallResult(data: List<MealsItem?>?) {
                val foodList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFood(foodList)
            }
    }.asFlow()

    override fun searchFood(query : String): Flow<List<Foods>> =
        localDataSource.searchFood(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }


    override fun getFavouriteFoods(): Flow<List<Foods>> {
        return localDataSource.getAllFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavouriteFoods(foods: Foods, state: Boolean) {
        val foodEntity = DataMapper.mapDomainToEntity(foods)
        withContext(Dispatchers.IO){
            localDataSource.updateFavoriteFood(foodEntity, state)
        }
    }

}