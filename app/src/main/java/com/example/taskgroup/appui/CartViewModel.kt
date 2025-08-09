package com.example.taskgroup.appui

import androidx.lifecycle.ViewModel
import com.example.taskgroup.Room.CartProduct
import com.example.taskgroup.Room.DAO

class CartViewModel(val dao: DAO) : ViewModel() {
    suspend fun getProducts() = dao.getProducts()
    suspend fun addProduct(product: CartProduct) = dao.upsertProduct(product)
    suspend fun deleteProduct(id: Int) = dao.deleteProduct(id)

}