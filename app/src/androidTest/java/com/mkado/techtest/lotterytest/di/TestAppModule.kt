package com.mkado.techtest.lotterytest.di

import android.content.Context
import androidx.room.Room
import com.mkado.techtest.lotterytest.data.local.room.LotteryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, LotteryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}