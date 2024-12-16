package com.example.mojakoszulka2.di

import android.content.Context
import androidx.room.Room
import com.example.mojakoszulka2.data.local.OrderDatabase
import com.example.mojakoszulka2.data.local.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OrderDatabase {
        return Room.databaseBuilder(
            context,
            OrderDatabase::class.java,
            "orders.db"
        ).build()
    }

    @Provides
    fun provideOrderDao(database: OrderDatabase): OrderDao {
        return database.orderDao()
    }
} 