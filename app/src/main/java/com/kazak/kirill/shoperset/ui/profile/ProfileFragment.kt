package com.kazak.kirill.shoperset.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentProfileBinding
import com.kazak.kirill.shoperset.util.customViews.CustomViewItemProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val vb: FragmentProfileBinding by viewBinding()
    private val vm by viewModel<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemLogOutClick()
    }

    private fun onItemLogOutClick() {
        vb.itemLogOut.onItemClickListener = object : CustomViewItemProfile.OnItemClickListener {

            override fun onItemClick() {
                val bottomNavigationView =
                    requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

                vm.deleteUserCredentials()
                bottomNavigationView.visibility = View.GONE
                findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
            }

        }
    }


}