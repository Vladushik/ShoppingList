package com.vladushik.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vladushik.shoppinglist.data.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllItems() : LiveData<List<ShoppingItem>>

}