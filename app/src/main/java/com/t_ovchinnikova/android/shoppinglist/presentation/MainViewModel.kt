package com.t_ovchinnikova.android.shoppinglist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.t_ovchinnikova.android.shoppinglist.data.ShopListRepositoryImpl
import com.t_ovchinnikova.android.shoppinglist.domain.*

class MainViewModel : ViewModel() {

    private val _shopList =  MutableLiveData<List<ShopItem>>()

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    var shopList: LiveData<List<ShopItem>> = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = shopItem.enabled.not())
        editShopItemUseCase.editShopItem(newItem)
    }


}