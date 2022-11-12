package edu.ucne.appliedbarbershop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.appliedbarbershop.data.local.AppDataBase
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
            "DATABASE_NAME"
        ).fallbackToDestructiveMigration().build()
    }
}