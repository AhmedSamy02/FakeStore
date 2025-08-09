package com.example.taskgroup.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProduct(product: CartProduct)

    @Query("SELECT * FROM cart_table")
    suspend fun getProducts(): List<CartProduct>

    @Delete
    suspend fun deleteProduct(product: CartProduct)

    @Query("DELETE FROM cart_table WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}