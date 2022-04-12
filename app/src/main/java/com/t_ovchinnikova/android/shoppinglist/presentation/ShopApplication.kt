package com.t_ovchinnikova.android.shoppinglist.presentation

import android.app.Application
import com.t_ovchinnikova.android.shoppinglist.di.DaggerApplicationComponent

class ShopApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}