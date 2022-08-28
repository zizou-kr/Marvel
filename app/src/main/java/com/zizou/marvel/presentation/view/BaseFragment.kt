package com.zizou.marvel.presentation.view

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}