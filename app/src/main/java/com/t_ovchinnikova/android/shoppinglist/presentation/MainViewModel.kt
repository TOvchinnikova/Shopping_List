package com.t_ovchinnikova.android.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.t_ovchinnikova.android.shoppinglist.data.ShopListRepositoryImpl
import com.t_ovchinnikova.android.shoppinglist.domain.*

class MainViewModel : ViewModel() {

    private val _shopList = MutableLiveData<List<ShopItem>>()
    val shopList: LiveData<List<ShopItem>> = _shopList

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        _shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = shopItem.enabled.not())
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

}