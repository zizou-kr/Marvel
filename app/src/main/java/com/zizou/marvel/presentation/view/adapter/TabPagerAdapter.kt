package com.zizou.marvel.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zizou.marvel.presentation.view.CharactersFragment
import com.zizou.marvel.presentation.view.FavoriteCharactersFragment

class TabPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharactersFragment()
            1 -> FavoriteCharactersFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }
}