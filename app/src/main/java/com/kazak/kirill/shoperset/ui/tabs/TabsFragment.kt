package com.kazak.kirill.shoperset.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {
    private val vb: FragmentTabsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_tabs) as NavHostFragment
        val navHost = navHostFragment.navController
        vb.bottomNavigationView.setupWithNavController(navHost)
    }

}