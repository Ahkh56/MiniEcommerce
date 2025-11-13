
package com.genesis.feature_cart.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.genesis.feature_cart.db.dao.CartDao
import com.genesis.feature_cart.db.entity.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
