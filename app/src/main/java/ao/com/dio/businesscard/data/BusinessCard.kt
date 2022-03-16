package ao.com.dio.businesscard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard (
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val nome:String,
    val tel:String,
    val email:String,
    val empresa:String,
    val fundoPersonalizado:String)

data class Colors (
    val cod:String,
    val cor:String

    ) {
    override fun toString(): String {
        return cor
    }
}