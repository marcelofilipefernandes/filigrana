package com.pm.filigrana.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pm.filigrana.data.entities.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT * FROM product ORDER BY id DESC")
    fun readAllProducts(): LiveData<List<Product>>

    @Delete
    fun deleteProduct(product: Product)
}