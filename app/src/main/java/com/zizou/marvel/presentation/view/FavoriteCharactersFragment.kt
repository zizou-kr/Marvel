package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zizou.marvel.R
import com.zizou.marvel.databinding.FragmentCharactersBinding
import com.zizou.marvel.presentation.model.CharacterUiModel
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.view.adapter.CharacterDiffUtil
import com.zizou.marvel.presentation.view.adapter.FavoriteCharacterAdapter
import com.zizou.marvel.presentation.view.adapter.FavoriteClickListener
import com.zizou.marvel.presentation.viewmodel.FavoriteCharactersViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteCharactersFragment : BaseFragment(), FavoriteClickListener {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: FavoriteCharactersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        subscribeViewModel()
    }

    private fun initializeUi() {
        val adapter = FavoriteCharacterAdapter(CharacterDiffUtil(), this)
        binding.recyclerCharacter.adapter = adapter
    }

    private fun subscribeViewModel() {
        viewModel.favoriteCharacters.observe(viewLifecycleOwner) {
            it?.let {
                (binding.recyclerCharacter.adapter as? FavoriteCharacterAdapter)?.submitList(it)
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

        viewModel.isSuccessFavoriteRemove
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val message = if (it) getString(R.string.favorite_remove_success) else getString(R.string.favorite_remove_fail)
                showMessage(message)
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)
    }

    override fun onFavoriteClick(character: CharacterUiModel, isFavorite: Boolean) {
        if (isFavorite) {
            viewModel.onClickFavorite(character)
        }
    }
}