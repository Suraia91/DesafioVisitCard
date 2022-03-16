package ao.com.dio.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ao.com.dio.businesscard.data.BusinessCard
import ao.com.dio.businesscard.data.BusinessCardRepository
import ao.com.dio.businesscard.data.Colors

class MainViewModel(private  val businessCardRepository: BusinessCardRepository):ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)
    }
    fun getAll():LiveData<List<BusinessCard>>{
        return  businessCardRepository.getAll()
    }
}
class MainViewModelFactory(private val repository: BusinessCardRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

       if(modelClass.isAssignableFrom(MainViewModel::class.java)){
           @Suppress("Unchecked_cast")
           return MainViewModel(repository) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}