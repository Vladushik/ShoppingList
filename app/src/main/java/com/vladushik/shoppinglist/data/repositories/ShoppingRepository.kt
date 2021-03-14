package com.vladushik.shoppinglist.data.repositories

import com.vladushik.shoppinglist.data.db.ShoppingDatabase
import com.vladushik.shoppinglist.data.entities.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsertItem(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().deleteItem(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllItems()
}