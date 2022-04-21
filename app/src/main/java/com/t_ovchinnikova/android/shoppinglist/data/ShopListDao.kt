package com.t_ovchinnikova.android.shoppinglist.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.t_ovchinnikova.android.shoppinglist.domain.ShopItem

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Query("SELECT * FROM shop_items")
    fun getShopListCursor(): Cursor

    @Insert(onConflict = REPLACE)
    suspend fun addShopItem(shopItem: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel
}