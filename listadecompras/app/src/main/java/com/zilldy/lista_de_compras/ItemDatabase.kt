package com.zilldy.lista_de_compras

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zilldy.lista_de_compras.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}
