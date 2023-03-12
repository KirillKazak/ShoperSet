package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentHomeBinding
import com.kazak.kirill.shoperset.util.Constants.BUNDLE_PRODUCT_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vb: FragmentHomeBinding by viewBinding()
    private val vm by viewModel<HomeViewModel>()
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val latestAdapter by lazy { LatestSearchAdapter() }
    private val flashSaleAdapter by lazy { FlashSaleAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCategory()
        startLatestSearchRecycler()
        startFlashSaleRecycler()
        onSearchingProducts()
        setUserPhotoToView()
    }

    private fun startCategory() {
        val defaultListCategory = arrayListOf(
            CategoryAdapter.Category(R.drawable.icon_phone, requireContext().getString(R.string.phones), false),
            CategoryAdapter.Category(R.drawable.icon_headphones, requireContext().getString(R.string.headphones), false),
            CategoryAdapter.Category(R.drawable.icon_games, requireContext().getString(R.string.games), false),
            CategoryAdapter.Category(R.drawable.icon_cars, requireContext().getString(R.string.cars), false),
            CategoryAdapter.Category(R.drawable.icon_furniture, requireContext().getString(R.string.furniture), false),
            CategoryAdapter.Category(R.drawable.icon_kids, requireContext().getString(R.string.kids), false)
        )

        vb.recyclerCategoryHome.adapter = categoryAdapter
        categoryAdapter.categoryList = defaultListCategory

        categoryAdapter.onCategoryItemClickListener = object : CategoryAdapter.OnCategoryItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onCategoryItemClick(position: Int) {
                val newListCategory = defaultListCategory
                newListCategory[position].isClick = true
                categoryAdapter.categoryList = newListCategory
            }
        }
    }

    private fun startLatestSearchRecycler() {
        vm.getLatestSearchProducts()
        vm.latestSearchProductsLD.observe(viewLifecycleOwner) {
            vb.recyclerLatestHome.adapter = latestAdapter
            latestAdapter.latestSearchList = it.latest
        }

        latestAdapter.onItemClickListener = object : LatestSearchAdapter.OnItemClickListener {
            override fun onItemClick() {
                startProductFragment()
            }
        }
    }

    private fun startFlashSaleRecycler() {
        vm.getSplashSaleProducts()
        vm.flashSaleProductsLD.observe(viewLifecycleOwner) {
            vb.recyclerFlashSaleHome.adapter = flashSaleAdapter
            flashSaleAdapter.flashSaleList = it.flash_sale
        }

        flashSaleAdapter.onItemClickListener = object : FlashSaleAdapter.OnItemClickListener {
            override fun onItemClick() {
                startProductFragment()
            }
        }
    }

    private fun startProductFragment() {
        vm.getInformationAboutProduct()
        vm.productInformationLD.observe(viewLifecycleOwner) {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_PRODUCT_KEY, it)
            findNavController().navigate(R.id.action_homeFragment_to_productFragment, bundle)
        }
    }

    private fun onSearchingProducts() {
        vm.getSearchingHints()

        vm.hintsLD.observe(viewLifecycleOwner) {
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it.words)
            vb.edtLookingForHome.setAdapter(arrayAdapter)
        }
    }

    private fun setUserPhotoToView() {
        Glide.with(this@HomeFragment)
            .load(vm.getUserPhoto())
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.icon_user_placeholder)
            .into(vb.ivUserPhotoHome)
    }
}