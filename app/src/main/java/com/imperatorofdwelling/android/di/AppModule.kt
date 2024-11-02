package com.imperatorofdwelling.android.di

fun appModule() = listOf(
    repositoryModule(),
    useCaseModule(),
    viewModelModule(),
    sharedPreferencesModule()
)