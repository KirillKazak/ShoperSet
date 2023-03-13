package com.kazak.kirill.shoperset.ui.profile

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentProfileBinding
import com.kazak.kirill.shoperset.domain.credentials.model.UserCredentialsModel
import com.kazak.kirill.shoperset.ui.MainActivity
import com.kazak.kirill.shoperset.util.customViews.CustomViewItemProfile
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var currentUserModel: UserCredentialsModel? = null
    private val vb: FragmentProfileBinding by viewBinding()
    private val vm by viewModel<ProfileViewModel>()
    private var uri = Uri.EMPTY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCurrentUserCredentialsLiveData()
        onItemLogOutClick()
        onBtnBackClick()
        onTvChangePhotoClick()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE &&
            resultCode == RESULT_OK && data != null) {

            uri = CropImage.getActivityResult(data).uri

            vm.saveUserCredentials(
                UserCredentialsModel(
                    userId = currentUserModel?.userId ?: 0,
                    userFirstName = currentUserModel?.userFirstName ?: getString(R.string.not_found),
                    userLastName = currentUserModel?.userLastName ?: getString(R.string.not_found),
                    userEmail = currentUserModel?.userEmail ?: getString(R.string.not_found),
                    userPhoto = uri.toString()
                )
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeCurrentUserCredentialsLiveData() {
        vm.currentUserCredentialsLiveData.observe(viewLifecycleOwner) {currentUserCredentials ->
            with(vb) {
                currentUserModel = currentUserCredentials

                tvUserNameProfile.text =
                    currentUserCredentials.userFirstName + " " + currentUserCredentials.userLastName

                Glide.with(this@ProfileFragment)
                    .load(currentUserCredentials.userPhoto)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.icon_user_placeholder)
                    .into(vb.ivUserPhotoProfile)
            }
        }
    }

    private fun onTvChangePhotoClick() {
        vb.tvChangePhotoProfile.setOnClickListener {
            changeUserPhoto()
        }
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(activity as MainActivity, this)

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

    private fun onBtnBackClick() {
        vb.ivArrowBackProfile.setOnClickListener {
//            requireActivity().findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        }
    }
}