package com.aramhakobyan.demo.chat.feature.chat

import android.os.Bundle
import com.aramhakobyan.demo.chat.base.BaseActivity
import com.aramhakobyan.demo.chat.data.model.Message
import com.aramhakobyan.demo.chat.databinding.ActivityChatBinding
import com.aramhakobyan.demo.chat.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : BaseActivity<ChatViewModel>() {

    private val binding: ActivityChatBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityChatBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var chatAdapter: ChatAdapter

    @Inject
    lateinit var numberAdapter: NumberAdapter

    override val viewModelClass: Class<ChatViewModel>
        get() = ChatViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

        viewModel.startTimer()
        viewModel.startUpdateTimer()
    }

    override suspend fun observeData(viewModel: ChatViewModel) {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is ChatUiState.MessageReceived -> {
                    chatAdapter.addMessage(
                        uiState.message
                    )
                    scrollChatToBottom()
                }

                is ChatUiState.MessageUpdated -> {
                    chatAdapter.updateMessageText()
                }
            }
        }
    }

    private fun initViews() {
        binding.run {
            numbers.adapter = numberAdapter
            chat.adapter = chatAdapter
            chat.isNestedScrollingEnabled = false

            commit.setOnClickListener { view ->
                if (input.text?.isNotBlank() == true) {
                    chatAdapter.addMessage(
                        Message(input.text.toString())
                    )
                }
                input.text = null
                hideKeyboard(view)
                scrollChatToBottom()
            }
        }
    }

    private fun scrollChatToBottom() {
        binding.chat.scrollToPosition(chatAdapter.itemCount - 1)
    }
}
