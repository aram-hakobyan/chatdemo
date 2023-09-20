package com.aramhakobyan.demo.chat.feature.chat

import com.aramhakobyan.demo.chat.base.BaseViewModel
import com.aramhakobyan.demo.chat.data.model.Message
import com.aramhakobyan.demo.chat.di.DefaultScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

private const val delay = 2000L
private const val period = 1000L

@HiltViewModel
class ChatViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    @DefaultScope
    lateinit var defaultScope: CoroutineScope

    private val _uiState = MutableSharedFlow<ChatUiState>(
        replay = REPLAY_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val uiState: SharedFlow<ChatUiState> = _uiState

    fun startTimer() {
        Timer().scheduleAtFixedRate(delay, period) {
            defaultScope.launch {
                _uiState.emit(
                    ChatUiState.MessageReceived(
                        Message("New Message")
                    )
                )
            }
        }
    }

    fun startUpdateTimer() {
        Timer().scheduleAtFixedRate(delay, delay) {
            defaultScope.launch {
                _uiState.emit(
                    ChatUiState.MessageUpdated
                )
            }
        }
    }
}

sealed class ChatUiState {
    data class MessageReceived(val message: Message) : ChatUiState()
    object MessageUpdated : ChatUiState()
}
