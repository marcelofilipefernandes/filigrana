package com.pm.filigrana.data.repository

import androidx.lifecycle.LiveData
import com.pm.filigrana.data.dao.ProductDao
import com.pm.filigrana.data.entities.Product

class ProductRepository(private  val productDao: ProductDao) {
    val readAllProducts : LiveData<List<Product>> = productDao.readAllProducts()

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }
}