package com.t_ovchinnikova.android.shoppinglist.di

import android.app.Application
import com.t_ovchinnikova.android.shoppinglist.data.AppDatabase
import com.t_ovchinnikova.android.shoppinglist.data.ShopListDao
import com.t_ovchinnikova.android.shoppinglist.data.ShopListRepositoryImpl
import com.t_ovchinnikova.android.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.newInstance(application).shopListDao()
        }
    }
}