package com.imperatorofdwelling.android.presentation.ui.user_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileViewModel(
    private val isRegisteredUseCase: IsRegisteredUseCase,
    private val getUserData: GetUserDataUseCase,
) : BaseViewModel<UserProfileViewModel.State>(State()) {

    init {
        _lce.value = LCE.Loading
        initUser(onFinished = {_lce.value = LCE.Idle})
    }

    private fun initUser(onFinished: () -> Unit) {
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
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }.invokeOnCompletion { onFinished() }
    }

    data class State(
        val user: UserDomain? = null
    )
}
