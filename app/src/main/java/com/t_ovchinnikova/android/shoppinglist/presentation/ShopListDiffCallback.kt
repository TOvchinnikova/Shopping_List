package com.t_ovchinnikova.android.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.t_ovchinnikova.android.shoppinglist.domain.ShopItem

class ShopListDiffCallback(
    val oldList: List<ShopItem>,
    val newList: List<ShopItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}