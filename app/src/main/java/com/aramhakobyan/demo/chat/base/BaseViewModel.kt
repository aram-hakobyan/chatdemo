package com.aramhakobyan.demo.chat.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    protected var viewModelJob: Job? = null

    open fun onDestroy() {
        viewModelJob?.cancel()
    }

    companion object {
        const val REPLAY_BUFFER_SIZE = 1
    }
}
