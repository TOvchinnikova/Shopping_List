package com.t_ovchinnikova.android.shoppinglist.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.t_ovchinnikova.android.shoppinglist.R
import com.t_ovchinnikova.android.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    var shopList = listOf<ShopItem>()
    set(value) {
        val callback = ShopListDiffCallback(shopList, value)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
    }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val layout = if (viewType == VIEW_TYPE_ENABLED) R.layout.item_shop_enabled else R.layout.item_shop_disabled
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)

        }

    }

    override fun onViewRecycled(viewHolder: ShopListViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
    }

    override fun getItemViewType(position: Int): Int {

        return if(shopList[position].enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED

    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }

    companion object {

        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 15

    }
}