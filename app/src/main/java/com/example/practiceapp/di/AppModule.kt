package com.example.practiceapp.di

import android.content.Context
import androidx.room.Room
import com.example.practiceapp.config.Config.APP_DATABASE_NAME
import com.example.practiceapp.data.api.RemoteSource
import com.example.practiceapp.data.api.ApiService
import com.example.practiceapp.data.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideTestApi(
        remoteDataSource: RemoteSource
    ): ApiService {
        return remoteDataSource.buildApi(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGetUserData(database: AppDatabase) = database.getUserData()

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
        .fallbackToDestructiveMigration().build()
}