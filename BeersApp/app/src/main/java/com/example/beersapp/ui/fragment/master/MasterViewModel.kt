package com.example.beersapp.ui.fragment.master
import android.util.Log
import androidx.lifecycle.*
import com.example.beersapp.entity.beer.Beer
import com.example.beersapp.entity.beer.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MasterViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : ViewModel() {

    private var _beers = MutableLiveData<MutableList<Beer>>()
    val beers : MutableLiveData<MutableList<Beer>> get() = _beers

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> get() = _errorMessage

    private var page : Int = 1

    fun getBeers(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val localBeers = withContext(Dispatchers.IO) { beerRepository.getCachedBeers() }
                if (localBeers.isNotEmpty()) {
                    val actualBeers = _beers.value ?: ArrayList()
                    actualBeers.addAll(localBeers)
                    _beers.value = actualBeers
                    page = getNextPage(localBeers)
                } else {
                    getMoreBears()
                }
            }
            catch (throwable: Throwable){
                getMoreBears()
            }
            _isLoading.value = false
        }
    }

    fun getMoreBears(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val beers = withContext(Dispatchers.IO) { beerRepository.getBeers(page) }
                val actualBeers = _beers.value ?: ArrayList()
                actualBeers.addAll(beers)
                _beers.value = actualBeers
                page++
                withContext(Dispatchers.IO) { beerRepository.saveBeersInCache(beers) }
            }catch (throwable : Throwable) { _errorMessage.value = throwable.message }
            _isLoading.value = false
        }
    }


    fun refreshBeers(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){beerRepository.deleteAllLocalBeers()}
            page = 1
            getMoreBears()
        }
    }


    private fun getNextPage(beers: List<Beer>) : Int {
        return beers.size / 25 + 1
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Viewmodel","oncleared")
    }

}