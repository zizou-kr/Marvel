package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import com.zizou.marvel.R
import com.zizou.marvel.databinding.FragmentCharactersBinding
import com.zizou.marvel.presentation.model.CharacterUiModel
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.view.adapter.CharacterAdapter
import com.zizou.marvel.presentation.view.adapter.CharacterDiffUtil
import com.zizou.marvel.presentation.view.adapter.FavoriteClickListener
import com.zizou.marvel.presentation.viewmodel.CharactersViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : BaseFragment(), FavoriteClickListener {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
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
        val adapter = CharacterAdapter(CharacterDiffUtil(), this)
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

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadData() }
        binding.recyclerCharacter.adapter = adapter
    }

    private fun subscribeViewModel() {
        viewModel.charactersPagingData.observe(viewLifecycleOwner) {
            it?.let {
                (binding.recyclerCharacter.adapter as? CharacterAdapter)?.submitData(lifecycle, it)
            } ?: viewModel.loadData()
        }

        viewModel.error
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showMessage("${it.message}")
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)

        viewModel.isSuccessFavoriteAdd
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val message = if (it) getString(R.string.favorite_add_success) else getString(R.string.favorite_add_fail)
                showMessage(message)
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)

        viewModel.isSuccessFavoriteRemove
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val message = if (it) getString(R.string.favorite_remove_success) else getString(R.string.favorite_remove_fail)
                showMessage(message)
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

    override fun onFavoriteClick(character: CharacterUiModel, isFavorite: Boolean) {
        if (isFavorite) {
            viewModel.onClickFavorite(character)
        } else {
            viewModel.onClickUnFavorite(character)
        }
    }
}