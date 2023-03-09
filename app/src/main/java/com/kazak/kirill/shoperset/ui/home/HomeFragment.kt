package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentHomeBinding
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
    }

    private fun startFlashSaleRecycler() {
        vm.getSplashSaleProducts()
        vm.flashSaleProductsLD.observe(viewLifecycleOwner) {
            vb.recyclerFlashSaleHome.adapter = flashSaleAdapter
            flashSaleAdapter.flashSaleList = it.flash_sale
        }
    }

}