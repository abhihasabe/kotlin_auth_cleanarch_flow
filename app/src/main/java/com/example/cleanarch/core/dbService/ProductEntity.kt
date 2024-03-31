package com.example.cleanarch.core.dbService


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "wishlist")
data class ProductEntity(
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @PrimaryKey(autoGenerate = false) @SerializedName("_id") val id: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("image") val image: String,
    @SerializedName("price") val price: Double,
    @SerializedName("ratings") val name: String,
    @SerializedName("__v") val v: Int,
) : Serializable