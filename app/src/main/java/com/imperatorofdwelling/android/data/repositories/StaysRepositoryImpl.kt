package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.ToStayData
import com.imperatorofdwelling.android.data.entities.mapper.StayDomainMapper
import com.imperatorofdwelling.android.data.local.preferences.CreatingHelpManager
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieManager
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class StaysRepositoryImpl(
    private val favouritesRepositoryImpl: FavouritesRepository,
    private val creatingHelpManager: CreatingHelpManager,
    private val cookieManager: CookieManager,
    private val locationRepository: LocationRepository,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
) : StaysRepository {
    override fun getAllStays(): NetworkResult<List<Stay>> {
        val favourites = getFavouritesList().values.flatten().map { it.id }.toSet()
        val result = ApiClient.getStay().getStays().execute()
        if (result.isSuccessful) {
            val bodyResult = result.body()?.data
            val mappedResult = StayDomainMapper.transform(bodyResult, favouritesData = favourites)
            return NetworkResult.Success(mappedResult)
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")
    }

    private fun getFavouritesList(): Map<String, List<Stay>> {
        val result = favouritesRepositoryImpl.getAllFavourites()
        return when (result) {
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

    override fun getCreatingHelp(): Flow<Boolean> {
        return creatingHelpManager.value
    }

    override fun updateCreatingHelp(value: Boolean) {
        creatingHelpManager.updateValue(value)
    }

    override fun getAllAmenities(): NetworkResult<List<String>> {
        val result = ApiClient.getStay().getAmenities().execute()
        return if (result.isSuccessful) {
            NetworkResult.Success(value = result.body()?.data ?: emptyList())
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override fun createStay(stay: Stay): NetworkResult<String> {
        val address = stay.street.split(",")[0]
        val locationId = locationRepository.getCities(address)
        if(locationId !is NetworkResult.Success){
            return NetworkResult.Error(errorMessage = "Invalid address city")
        }
        val userId = sharedPreferencesDataSource.getString(AuthRepositoryImpl.ID_KEY, "")
        val stayCreating = stay.copy(locationId = locationId.value[0].id, userId = userId)
        val result = ApiClient.getStay().createStay(stayCreating.ToStayData(), cookieManager.getCookie()).execute()
        return if (result.isSuccessful) {
            NetworkResult.Success(value = result.body()?.data ?: "")
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override fun createStayImage(image: Avatar, stayId: String): NetworkResult<String> {
        val requestData = image.bytes.toRequestBody(image.mimeType.toMediaTypeOrNull())
        val multipart = MultipartBody.Part.createFormData("image", image.name, requestData)
        val stayIdMultipart = MultipartBody.Part.createFormData("text", stayId)
        val result = ApiClient.getStay().createMainImage(
            cookies = cookieManager.getCookie(),
            image = multipart,
            stayId = stayIdMultipart
        ).execute()
        return if (result.isSuccessful) {
            NetworkResult.Success(value = result.body()?.data ?: "")
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override fun getStaysByUserId(): NetworkResult<List<Stay>> {
        TODO("Not yet implemented")
    }
}