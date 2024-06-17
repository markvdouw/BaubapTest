package com.mark.baubapchallenge.di

import android.content.Context
import com.mark.baubapchallenge.view.BaubapTestApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaubapTestApp = app as BaubapTestApp


    @Provides
    @Singleton
    fun provideContext(application: BaubapTestApp): Context = application.applicationContext

}
