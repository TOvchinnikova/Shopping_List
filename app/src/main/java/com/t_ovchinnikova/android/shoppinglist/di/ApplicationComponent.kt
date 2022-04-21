package com.t_ovchinnikova.android.shoppinglist.di

import android.app.Application
import com.t_ovchinnikova.android.shoppinglist.data.ShopListProvider
import com.t_ovchinnikova.android.shoppinglist.presentation.MainActivity
import com.t_ovchinnikova.android.shoppinglist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}