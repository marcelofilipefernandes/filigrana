package com.pm.filigrana.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)