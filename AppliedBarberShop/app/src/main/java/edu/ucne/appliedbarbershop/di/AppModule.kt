package edu.ucne.appliedbarbershop.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.repository.BarberoRepository
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository
import edu.ucne.appliedbarbershop.data.remote.api_dao.BarberoApi
import edu.ucne.appliedbarbershop.data.remote.api_dao.CitaApi
import edu.ucne.appliedbarbershop.data.remote.api_dao.ClienteApi
import edu.ucne.appliedbarbershop.data.remote.api_dao.ServicioApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context) : AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "AppliedBarberShop_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesBarberoApi(moshi: Moshi): BarberoApi {
        return Retrofit.Builder()
            .baseUrl("https://apibarbershop.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BarberoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCitaApi(moshi: Moshi): CitaApi {
        return Retrofit.Builder()
            .baseUrl("https://apibarbershop.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CitaApi::class.java)
    }

    @Singleton
    @Provides
    fun providesPerfilApi(moshi: Moshi): ClienteApi {
        return Retrofit.Builder()
            .baseUrl("https://apibarbershop.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ClienteApi::class.java)
    }

    @Singleton
    @Provides
    fun providesServicioApi(moshi: Moshi): ServicioApi {
        return Retrofit.Builder()
            .baseUrl("https://apibarbershop.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ServicioApi::class.java)
    }

    @Singleton
    @Provides
    fun ProvideBarberoRepository(appDataBase: AppDataBase): BarberoRepository {
        return BarberoRepository(appDataBase)
    }

    @Singleton
    @Provides
    fun ProvideCitaRepository(appDataBase: AppDataBase): CitaRepository {
        return CitaRepository(appDataBase)
    }

    @Singleton
    @Provides
    fun ProvidePerfilRepository(appDataBase: AppDataBase): ClienteRepository {
        return ClienteRepository(appDataBase)
    }

    @Singleton
    @Provides
    fun ProvideServicioRepository(appDataBase: AppDataBase): ServicioRepository {
        return ServicioRepository(appDataBase)
    }
}