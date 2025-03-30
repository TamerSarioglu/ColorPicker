package com.tamersarioglu.colorpicker.di

import android.content.Context
import com.tamersarioglu.colorpicker.data.mapper.ColorMapper
import com.tamersarioglu.colorpicker.data.source.ImageColorExtractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideColorMapper(): ColorMapper {
        return ColorMapper()
    }

    @Provides
    @Singleton
    fun provideImageColorExtractor(@ApplicationContext context: Context): ImageColorExtractor {
        return ImageColorExtractor(context)
    }
}