package com.raf.mydrink.di

import com.raf.mydrink.MainViewModel
import com.raf.mydrink.core.domain.usecase.DrinkInteractor
import com.raf.mydrink.core.domain.usecase.DrinkUseCase
import com.raf.mydrink.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DrinkUseCase> { DrinkInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}