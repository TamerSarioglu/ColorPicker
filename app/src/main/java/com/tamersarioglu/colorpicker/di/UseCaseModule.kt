package com.tamersarioglu.colorpicker.di

import android.content.Context
import com.tamersarioglu.colorpicker.domain.repository.ColorRepository
import com.tamersarioglu.colorpicker.domain.usecase.ExtractColorsUseCase
import com.tamersarioglu.colorpicker.domain.usecase.GetImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideExtractColorsUseCase(repository: ColorRepository): ExtractColorsUseCase {
        return ExtractColorsUseCase(repository)
    }

    @Provides
    fun provideGetImageUseCase(@ApplicationContext context: Context): GetImageUseCase {
        return GetImageUseCase(context)
    }
}