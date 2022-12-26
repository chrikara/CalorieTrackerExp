package com.chriskaras.mycalorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.chriskaras.mycalorietracker.data.preferences.DefaultPreferences
import com.chriskaras.mycalorietracker.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // This lives as long as our application does
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app : Application
    ) : SharedPreferences{
        return app.getSharedPreferences("shared_prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences) : Preferences{
        return DefaultPreferences(sharedPreferences)
    }
}