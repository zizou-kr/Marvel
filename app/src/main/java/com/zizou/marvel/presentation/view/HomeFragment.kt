package com.zizou.marvel.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zizou.marvel.R
import com.zizou.marvel.databinding.FragmentHomeBinding
import com.zizou.marvel.presentation.view.adapter.TabPagerAdapter

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

    private fun initializeUi() {
        val sectionsPagerAdapter = TabPagerAdapter(this)

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = getTabText(position)
        }.attach()
    }

    private fun getTabText(position: Int): String = when (position) {
        0 -> getString(R.string.tab_characters)
        1 -> getString(R.string.tab_favorite)
        else -> ""
    }
}