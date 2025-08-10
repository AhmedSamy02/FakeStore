package com.example.taskgroup.Room

class CartRepo(private val dao: DAO) {

    suspend fun addOrUpdateProduct(product: CartProduct) {
        val existingProducts = dao.getProducts()
        val existing = existingProducts.find { it.id == product.id }

        if (existing != null) {

            dao.upsertProduct(existing.copy(quantity = existing.quantity + 1))
        } else {
            dao.upsertProduct(product)
        }
    }

    suspend fun getCartProducts(): List<CartProduct> = dao.getProducts()

    suspend fun deleteProduct(product: CartProduct) = dao.deleteProduct(product)

    suspend fun deleteProductById(id: Int) = dao.deleteProduct(id)
}
