package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    val postFragment = PostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabLayout>(R.id.main_tab)
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_home))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_post))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_my))

        val pager = findViewById<ViewPager2>(R.id.main_pager)
        pager.adapter = MainPagerAdapter(this,3, postFragment)

        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.setCurrentItem(tab!!.position)
                if(tab!!.position == 1) {
                    postFragment.makePost()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}

class MainPagerAdapter(
    fragmentActivity: FragmentActivity,
    val tabCount: Int,
    val postFragment: PostFragment
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return FeedFragment()
            1 -> return postFragment
            else -> return ProfileFragment()
        }
    }

}