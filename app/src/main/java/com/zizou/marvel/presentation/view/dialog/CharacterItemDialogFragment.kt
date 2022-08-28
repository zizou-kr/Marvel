package com.zizou.marvel.presentation.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.zizou.marvel.R
import com.zizou.marvel.databinding.DialogCharacterItemBinding
import com.zizou.marvel.domain.model.Character
import com.zizou.marvel.presentation.showMessage
import com.zizou.marvel.presentation.viewmodel.CharacterItemViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CharacterItemDialogFragment : DialogFragment() {
    private val args: CharacterItemDialogFragmentArgs by navArgs()
    private val viewModel: CharacterItemViewModel by viewModel()
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = it.layoutInflater
            val character = args.character

            viewModel.initialize(character)
            subscribeViewModel()

            val binding = DialogCharacterItemBinding.inflate(inflater)
            binding.viewModel = viewModel
            binding.buttonThumbnail.setOnClickListener { viewModel.downloadImage() }
            binding.buttonUrl.setOnClickListener { navigateCharacterItemsFragment(character, Type.URL) }
            binding.buttonComics.setOnClickListener { navigateCharacterItemsFragment(character, Type.COMIC) }
            binding.buttonStories.setOnClickListener { navigateCharacterItemsFragment(character, Type.STORY) }
            binding.buttonEvents.setOnClickListener { navigateCharacterItemsFragment(character, Type.EVENT) }
            binding.buttonSeries.setOnClickListener { navigateCharacterItemsFragment(character, Type.SERIES) }

            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun subscribeViewModel() {
        viewModel.isDownloadSuccess
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it) {
                    showMessage(getString(R.string.image_download_success))
                } else {
                    showMessage(getString(R.string.image_download_fail))
                }
            }) {
                showMessage(getString(R.string.image_download_fail))
            }
            .addTo(disposables)

        viewModel.workerRequestId
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                observeImageDownloadWorker(it)
            }) {
                showMessage("${it.message}")
            }
            .addTo(disposables)
    }

    private fun observeImageDownloadWorker(id: UUID) {
        WorkManager.getInstance(requireActivity().applicationContext)
            .getWorkInfoByIdLiveData(id)
            .observe(this) {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        viewModel.onDownloadSuccess()
                    }

                    WorkInfo.State.FAILED, WorkInfo.State.CANCELLED -> {
                        viewModel.onDownloadFailed()
                    }
                }
            }
    }

    private fun navigateCharacterItemsFragment(character: Character, type: Type) {
        val characterId = character.id

        characterId?.let {
            val action = when (type) {
                Type.SERIES -> CharacterItemDialogFragmentDirections.actionCharacterItemsDialogFragmentToCharacterSeriesFragment(characterId)
                Type.COMIC -> CharacterItemDialogFragmentDirections.actionCharacterItemsDialogFragmentToCharacterComicsFragment(characterId)
                Type.EVENT -> CharacterItemDialogFragmentDirections.actionCharacterItemsDialogFragmentToCharacterEventsFragment(characterId)
                Type.STORY -> CharacterItemDialogFragmentDirections.actionCharacterItemsDialogFragmentToCharacterStoriesFragment(characterId)
                Type.URL -> CharacterItemDialogFragmentDirections.actionCharacterItemsDialogFragmentToWebsiteFragment(character.getWebUrl())
            }

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    enum class Type {
        SERIES, COMIC, EVENT, STORY, URL
    }
}