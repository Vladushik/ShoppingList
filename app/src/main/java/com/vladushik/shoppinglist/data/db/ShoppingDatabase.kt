package com.vladushik.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vladushik.shoppinglist.data.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    companion object{
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "Shopping.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java, DB_NAME).build()

    }

    abstract fun getShoppingDao(): ShoppingDao

}