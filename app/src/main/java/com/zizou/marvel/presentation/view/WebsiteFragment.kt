package com.zizou.marvel.presentation.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zizou.marvel.databinding.FragmentWebsiteBinding
import com.zizou.marvel.presentation.viewmodel.WebsiteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebsiteFragment : BaseFragment() {
    private lateinit var binding: FragmentWebsiteBinding
    private val viewModel: WebsiteViewModel by viewModel()
    private val args: WebsiteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebsiteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkData()
        initializeUi()
    }

    private fun checkData() {
        val url = args.url
        if (url.isBlank()) {
            findNavController().popBackStack()
        }
    }

    private fun initializeUi() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                findNavController().popBackStack()
            }
        }

        binding.webView.run {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    viewModel.onLoadingStateChanged(true)
                }

                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                    viewModel.onLoadingStateChanged(false)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    viewModel.onLoadingStateChanged(false)
                }
            }
            loadUrl(args.url)
        }
    }
}