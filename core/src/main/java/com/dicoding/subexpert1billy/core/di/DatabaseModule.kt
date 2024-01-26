package com.dicoding.subexpert1billy.core.di

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import androidx.room.Room
import com.dicoding.subexpert1billy.core.data.source.local.room.FoodDao
import com.dicoding.subexpert1billy.core.data.source.local.room.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FoodDatabase {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("food".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java, "Food.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }


    @Provides
    fun provideFoodDao(database: FoodDatabase) : FoodDao = database.FoodDao()

}