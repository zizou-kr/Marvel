package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.zizou.marvel.R
import com.zizou.marvel.databinding.FragmentCharacterEventsBinding
import com.zizou.marvel.domain.model.Event
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.view.adapter.EventAdapter
import com.zizou.marvel.presentation.viewmodel.CharacterEventsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterEventsFragment : CharacterItemsFragment(), EventAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCharacterEventsBinding
    private val viewModel: CharacterEventsViewModel by viewModel()
    private val args: CharacterEventsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterEventsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        subscribeViewModel()
    }

    private fun initializeUi() {
        val adapter = EventAdapter(this, EventAdapter.EventComparator())
        adapter.addLoadStateListener {
            val refresh = it.refresh
            val append = it.append
            val isLoadFinished = (append is LoadState.NotLoading) && (append.endOfPaginationReached)
            val isEmptyResult = isLoadFinished && adapter.itemCount == 0

            viewModel.onLoadingStateChanged((append is LoadState.Loading) || (refresh is LoadState.Loading))

            if (isEmptyResult) {
                viewModel.onPageEmpty()
            } else {
                viewModel.onPageLoad(isLoadFinished)
            }

            if (refresh is LoadState.Error) viewModel.onError(refresh.error)
            if (append is LoadState.Error) viewModel.onError(append.error)

            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadData(args.characterId) }

        binding.recyclerEvent.adapter = adapter
    }

    private fun subscribeViewModel() {
        viewModel.eventsPagingData.observe(viewLifecycleOwner) {
            it?.let {
                (binding.recyclerEvent.adapter as? EventAdapter)?.submitData(lifecycle, it)
            } ?: viewModel.loadData(args.characterId)
        }

        viewModel.error
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showMessage("${it.message}")
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)

        viewModel.isEndOfPaging
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showMessage(getString(R.string.paging_finished))
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)

    }

    override fun onItemClick(event: Event) {
        val action = CharacterEventsFragmentDirections.actionCharacterEventsFragmentToEventDetailFragment(event)
        findNavController().navigate(action)
    }
}