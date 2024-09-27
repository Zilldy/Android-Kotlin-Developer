package zilldy.com.github.cryptomonitor.state

import zilldy.com.github.cryptomonitor.service.Ticker

sealed class ScreenState {
    object Loading: ScreenState()
    data class Success(val data: Ticker): ScreenState()
    data class Error(val exception: Throwable): ScreenState()
}