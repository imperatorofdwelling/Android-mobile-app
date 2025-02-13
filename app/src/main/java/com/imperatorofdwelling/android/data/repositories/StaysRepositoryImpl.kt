package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.mapper.StayDomainMapper
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class StaysRepositoryImpl(private val favouritesRepositoryImpl: FavouritesRepository) : StaysRepository {
    override fun getAllStays(): NetworkResult<List<Stay>> {
        val favourites = getFavouritesList().values.flatten().map{it.id}.toSet()
        val result = ApiClient.getStay().getStays().execute()
        if (result.isSuccessful) {
            val bodyResult = result.body()?.data
            val mappedResult = StayDomainMapper.transform(bodyResult, favouritesData = favourites)
            return NetworkResult.Success(mappedResult)
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")
    }

    private fun getFavouritesList(): Map<String, List<Stay>>{
        val result = favouritesRepositoryImpl.getAllFavourites()
        return when(result){
            is NetworkResult.Success -> result.value
            is NetworkResult.Error -> emptyMap()
        }
    }

    override fun getStaysByLocation(locationId: String): NetworkResult<List<Stay>> {
        val favourites = getFavouritesList().values.flatten().map { it.id }.toSet()
        val result = ApiClient.getStay().getStaysByLocation(locationId).execute()
        if (result.isSuccessful) {
            val bodyResult = result.body()?.data
            val mappedResult = StayDomainMapper.transform(bodyResult, favouritesData = favourites)
            return NetworkResult.Success(mappedResult)
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")
    }

    override fun getMainImage(id: String): NetworkResult<String> {
        val result = ApiClient.getStay().getMainImage(id).execute()
        return if (result.isSuccessful) {
            NetworkResult.Success(value = ApiClient.BASE_FILE_URL + result.body()?.data?.imageName.toString())
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override fun getStayById(id: String): NetworkResult<Stay> {
        val favourites = getFavouritesList().values.flatten().map { it.id }.toSet()
        val result = ApiClient.getStay().getStayById(id).execute()
        return if (result.isSuccessful) {
            NetworkResult.Success(
                StayDomainMapper.transform(
                    item = result.body()?.data,
                    isFavourites = favourites.contains(result.body()?.data?.id)
                )!!
            )
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }
}