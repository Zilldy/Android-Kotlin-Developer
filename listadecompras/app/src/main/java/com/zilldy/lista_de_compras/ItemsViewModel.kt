package com.zilldy.lista_de_compras

import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    private var items = mutableListOf<ItemModel>()

    fun addItem(name: String) {
        val item = ItemModel(
            name = name,
            onRemove = ::removeItem
        )

        items.add(item)
    }

    private fun removeItem(item: ItemModel) {
        items.remove(item)
    }
}
