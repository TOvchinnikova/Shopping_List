package com.t_ovchinnikova.android.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t_ovchinnikova.android.shoppinglist.domain.DeleteShopItemUseCase
import com.t_ovchinnikova.android.shoppinglist.domain.EditShopItemUseCase
import com.t_ovchinnikova.android.shoppinglist.domain.GetShopListUseCase
import com.t_ovchinnikova.android.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

    var shopList: LiveData<List<ShopItem>> = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = shopItem.enabled.not())
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}