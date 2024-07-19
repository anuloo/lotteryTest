package com.mkado.techtest.lotterytest.di

import android.content.Context
import androidx.room.Room
import com.mkado.techtest.lotterytest.data.local.room.LotteryDao
import com.mkado.techtest.lotterytest.data.local.room.LotteryDatabase
import com.mkado.techtest.lotterytest.data.remote.api.LotteryApi
import com.mkado.techtest.lotterytest.data.repository.LotteryRepositoryImp
import com.mkado.techtest.lotterytest.data.service.LotteryService
import com.mkado.techtest.lotterytest.domain.repository.LotteryRepository
import com.mkado.techtest.lotterytest.domain.uscase.DrawUseCase
import com.mkado.techtest.lotterytest.domain.uscase.GenerateDrawUseCase
import com.mkado.techtest.lotterytest.domain.uscase.GenerateQRCodeUseCase
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryByIdUseCase
import com.mkado.techtest.lotterytest.domain.uscase.GetLotteryDataUseCase
import com.mkado.techtest.lotterytest.domain.uscase.LotteryByIdUseCase
import com.mkado.techtest.lotterytest.domain.uscase.LotteryUsecase
import com.mkado.techtest.lotterytest.domain.uscase.QRCodeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideLotteryApi(client: HttpClient): LotteryApi {
        return LotteryApi(client)
    }

    @Provides
    @Singleton
    fun provideLotteryService(api: LotteryApi): LotteryService {
        return LotteryService(api)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LotteryDatabase {
        return Room.databaseBuilder(
            context,
            LotteryDatabase::class.java,
            "lottery_db"
        ).build()
    }

    @Provides
    fun provideLotteryDao(database: LotteryDatabase): LotteryDao {
        return database.lotteryDao()
    }

    @Provides
    @Singleton
    fun provideLotteryRepository(
        service: LotteryService,
        dao: LotteryDao
    ): LotteryRepository {
        return LotteryRepositoryImp(service, dao)
    }

    @Provides
    @Singleton
    fun provideLotteryUseCase(repository: LotteryRepository): LotteryUsecase {
        return GetLotteryDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetLotteryByIdUseCase(repository: LotteryRepository): LotteryByIdUseCase {
        return GetLotteryByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGenerateDrawUseCase(repository: LotteryRepository): DrawUseCase {
        return GenerateDrawUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGenerateQRCodeUseCase(repository: LotteryRepository): QRCodeUseCase {
        return GenerateQRCodeUseCase(repository)
    }

}

