package com.chriskaras.mycalorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.chriskaras.mycalorietracker.data.local.TrackerDatabase
import com.chriskaras.mycalorietracker.data.preferences.DefaultPreferences
import com.chriskaras.mycalorietracker.data.preferences.Preferences
import com.chriskaras.mycalorietracker.data.remote.OpenFoodApi
import com.chriskaras.mycalorietracker.features.tracker.data.repository.TrackerRepositoryImpl
import com.chriskaras.mycalorietracker.features.tracker.domain.repository.TrackerRepository
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.CalculateMealNutrients
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.DeleteTrackedFood
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.GetFoodsForDate
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.SearchFood
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.TrackFood
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.TrackerUseCases
import com.chriskaras.mycalorietracker.use_cases.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.logging.Filter
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

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase() : FilterOutDigits{
        return FilterOutDigits()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }


    @Module
    @InstallIn(ViewModelComponent::class)
    object TrackerDomainModule {

        @ViewModelScoped
        @Provides
        fun provideTrackerUseCases(
            repository: TrackerRepository,
            preferences: Preferences
        ): TrackerUseCases {
            return TrackerUseCases(
                trackFood = TrackFood(repository),
                searchFood = SearchFood(repository),
                getFoodsForDate = GetFoodsForDate(repository),
                deleteTrackedFood = DeleteTrackedFood(repository),
                calculateMealNutrients = CalculateMealNutrients(preferences)
            )
        }
    }
}