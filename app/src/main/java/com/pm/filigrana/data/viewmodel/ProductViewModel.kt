package com.pm.filigrana.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pm.filigrana.data.database.FiligranaDatabase
import com.pm.filigrana.data.entities.Product
import com.pm.filigrana.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application){
    val readAllProducts: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDao = FiligranaDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllProducts = repository.readAllProducts
    }

    fun  addProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }

    fun  deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
        }
    }
}