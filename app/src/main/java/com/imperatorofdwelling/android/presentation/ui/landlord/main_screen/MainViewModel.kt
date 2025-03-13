package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.usecases.EditUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getUserAvatarUseCase: GetUserAvatarUseCase,
    private val editUserAvatarUseCase: EditUserAvatarUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val isRegisteredUseCase: IsRegisteredUseCase
): BaseViewModel<MainViewModel.State>(State()) {

    init {
        updateIsRegistered()
        initUser()
        updateAvatar()
    }

    private fun updateAvatar() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (val result = getUserAvatarUseCase()) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(userAvatarUrl = result.value)
                            }
                            Log.e("GetUserAvatar", result.value)
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.e("GetUserError", result.errorMessage)
                    }
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }
    }


    fun onAvatarSelected(data: ByteArray, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val avatar = Avatar(name = "image", mimeType = type, bytes = data)
                when (val result = editUserAvatarUseCase(avatar)) {
                    is NetworkResult.Success -> {
                        updateAvatar()
                        Log.e("editAvatarSuccess", "${result.value}")
                    }

                    is NetworkResult.Error -> {
                        Log.e("editAvatarError", result.errorMessage)
                    }
                }
            }.onFailure { e ->
                Log.e("editAvatarServerError", e.message.toString())
            }
        }
    }

    private fun initUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (val result = getUserDataUseCase()) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(userData = result.value)
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.e("GetUserError", result.errorMessage)
                    }
                }
                _lce.value = LCE.Idle
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }
    }

    private fun updateIsRegistered() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _state.update {
                    it.copy(isRegistered = isRegisteredUseCase())
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }
    }

    data class State(
        val userAvatarUrl: String = "",
        val userData: UserDomain? = null,
        val isRegistered: Boolean = false,

    )
}