package com.tamersarioglu.colorpicker.di

import com.tamersarioglu.colorpicker.data.mapper.ColorMapper
import com.tamersarioglu.colorpicker.data.repository.ColorRepositoryImpl
import com.tamersarioglu.colorpicker.data.source.ImageColorExtractor
import com.tamersarioglu.colorpicker.domain.repository.ColorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideColorRepository(
        imageColorExtractor: ImageColorExtractor,
        colorMapper: ColorMapper
    ): ColorRepository {
        return ColorRepositoryImpl(imageColorExtractor, colorMapper)
    }
}
