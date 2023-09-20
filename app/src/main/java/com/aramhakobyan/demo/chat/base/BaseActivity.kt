package com.aramhakobyan.demo.chat.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

abstract class BaseActivity<ViewModel : BaseViewModel> : FragmentActivity() {

    protected abstract val viewModelClass: Class<ViewModel>

    protected open val viewModel: ViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this)[viewModelClass]
    }

    protected abstract suspend fun observeData(viewModel: ViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeData(viewModel)
            }
        }
    }
}
