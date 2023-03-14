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
import com.kazak.kirill.shoperset.ui.MainActivity
import com.kazak.kirill.shoperset.util.customViews.CustomViewItemProfile
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {
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

            vm.saveUserPhoto(uri.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeCurrentUserCredentialsLiveData() {
        vm.userNameAndPhotoLiveData.observe(viewLifecycleOwner) {currentUserCredentials ->
            with(vb) {

                tvUserNameProfile.text = currentUserCredentials.userName

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
                requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
            }

        }
    }

    private fun onBtnBackClick() {
        vb.ivArrowBackProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}