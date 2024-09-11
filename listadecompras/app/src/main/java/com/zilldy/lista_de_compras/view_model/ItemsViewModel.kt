package com.zilldy.lista_de_compras.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zilldy.lista_de_compras.model.ItemModel

class ItemsViewModel(application: Application) : ViewModel() {

    private var items = mutableListOf<ItemModel>()
    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name: String) {
        val item = ItemModel(
            id = 0,
            name = name,
            onRemove = ::removeItem
        )

        if (!items.contains(item)) {
            items.add(item)
            itemsLiveData.value = items
        }
    }

    private fun removeItem(item: ItemModel) {
        items.remove(item)
        itemsLiveData.value = items
    }
}
