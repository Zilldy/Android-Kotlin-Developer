package zilldy.com.github.cryptomonitor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import zilldy.com.github.cryptomonitor.service.CryptoService
import zilldy.com.github.cryptomonitor.state.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoViewModel(
    private val service: CryptoService
) : ViewModel() {
    private val _tickerLiveData = MutableLiveData<ScreenState>()
    val tickerLiveData: LiveData<ScreenState> = _tickerLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetch()
        }
    }

    private suspend fun fetch() {
        _tickerLiveData.postValue(ScreenState.Loading)
        try {
            val response = service.fetchCoinTicker()
            if (response.isSuccessful) {
                val tickerResponse = response.body()
                if (tickerResponse != null) {
                    _tickerLiveData.postValue(
                        ScreenState.Success(
                            data = tickerResponse.ticker
                        )
                    )
                }
            }
        } catch (exception: Throwable) {
            _tickerLiveData.postValue(
                ScreenState.Error(
                    exception
                )
            )
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            fetch()
        }
    }
}