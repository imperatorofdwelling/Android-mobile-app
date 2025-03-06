package com.imperatorofdwelling.android.data.local.preferences

import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RoleManager(private val preferences: SharedPreferencesDataSource) {

    companion object {
        const val TENANT_ROLE = 0
    }

    private val _data = MutableStateFlow(getUserRole())

    val data: StateFlow<Int> = _data

    private fun getUserRole(): Int {
        return preferences.getInt(AuthRepositoryImpl.ROLE_KEY, TENANT_ROLE)
    }

    fun setUserRole(role: Int) {
        preferences.putInt(AuthRepositoryImpl.ROLE_KEY, role)
        _data.value = role
    }

    fun getUserRoleFlow(): Flow<Int> {
        return data
    }


}