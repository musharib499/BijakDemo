package com.innobles.bijakmusharib.ui.main.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.innobles.bijakmusharib.ui.main.view.MainActivity
import com.innobles.bijakmusharib.ui.main.view.MainFragment
import com.innobles.bijakmusharib.ui.main.view.SourceFragment

/**
 * Created by Musharib Ali on 8/11/20.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
class ViewPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment.newInstance()
            else -> SourceFragment.newInstance()
        }

    }

}