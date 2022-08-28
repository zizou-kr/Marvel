package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.zizou.marvel.databinding.FragmentStoryDetailBinding
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.viewmodel.StoryDetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoryDetailFragment : DetailFragment() {
    private lateinit var binding: FragmentStoryDetailBinding
    private val viewModel: StoryDetailViewModel by viewModel()
    private val args: StoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.story.observe(viewLifecycleOwner) {
            it?.let {
                binding.story = it
            } ?: viewModel.initialize(args.story)
        }

        viewModel.error
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showMessage("${it.message}")
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)
    }
}