package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.zizou.marvel.databinding.FragmentSeriesDetailBinding
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.viewmodel.SeriesDetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesDetailFragment : DetailFragment() {
    private lateinit var binding: FragmentSeriesDetailBinding
    private val viewModel: SeriesDetailViewModel by viewModel()
    private val args: SeriesDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeriesDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.series.observe(viewLifecycleOwner) {
            it?.let {
                binding.series = it
            } ?: viewModel.initialize(args.series)
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