package com.imperatorofdwelling.android.presentation.ui.user_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.auth.usecases.LogOutUseCase
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.usecases.EditUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserRoleUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.domain.user.usecases.SetUserRoleUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileViewModel(
    private val isRegisteredUseCase: IsRegisteredUseCase,
    private val getUserData: GetUserDataUseCase,
    private val getUserAvatarUseCase: GetUserAvatarUseCase,
    private val editUserAvatarUseCase: EditUserAvatarUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val getUserRoleUseCase: GetUserRoleUseCase,
    private val setUserRoleUseCase: SetUserRoleUseCase,
) : BaseViewModel<UserProfileViewModel.State>(State()) {

    init {
        updateState()
    }

    fun updateState() {
        _lce.value = LCE.Loading
        updateIsRegistered()
        updateAvatar()
        initUser()
        updateUserRole()
    }

    private fun updateUserRole() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserRoleUseCase().collect { role ->
                _state.update {
                    it.copy(userRole = role)
                }
            }
        }
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

    private fun initUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (val result = getUserData()) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(user = result.value)
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

    fun onLogOutClicked() {
        logOutUseCase()
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

    fun onRoleSwitch(role: Int) {
        _state.update { it.copy(userRole = role) }
        viewModelScope.launch(Dispatchers.IO) {
            setUserRoleUseCase(role)
        }
    }

    data class State(
        val user: UserDomain? = null,
        val isRegistered: Boolean = false,
        val userAvatarUrl: String? = null,
        val userRole: Int = 0,
    )
}
