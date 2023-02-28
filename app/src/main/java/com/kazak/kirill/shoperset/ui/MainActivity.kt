package com.kazak.kirill.shoperset.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val vb: ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment)
        vb.bottomNavigationView.setupWithNavController(navController)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            val uri = CropImage.getActivityResult(data).uri
//            Glide.with(this)
//                .load(uri)
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.icon_user_placeholder)
//                .into(findViewById(R.id.iv_user_photo_profile))
//        }
//    }
}