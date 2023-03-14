package com.kazak.kirill.shoperset.ui.home

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
    private val categoryAdapter by lazy { CategoryAdapter(requireContext()) }
    private val latestAdapter by lazy { LatestSearchAdapter() }
    private val flashSaleAdapter by lazy { FlashSaleAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getProducts(listOf())
        startLatestSearchRecycler()
        startFlashSaleRecycler()
        startCategory()
        onSearchingProducts()
        setUserPhotoToView()
    }

    private fun startCategory() {

        vb.recyclerCategoryHome.adapter = categoryAdapter

        categoryAdapter.onCategoryItemClickListener = object : CategoryAdapter.OnCategoryItemClickListener {
            override fun onCategoryItemClick(activeCategories: List<String>) {
                vm.getProducts(activeCategories)
            }
        }
    }

    private fun startLatestSearchRecycler() {
        vm.latestSearchProductsLD.observe(viewLifecycleOwner) {
            vb.recyclerLatestHome.adapter = latestAdapter
            latestAdapter.latestSearchList = it
        }

        latestAdapter.onItemClickListener = object : LatestSearchAdapter.OnItemClickListener {
            override fun onItemClick() {
                startProductFragment()
            }
        }
    }

    private fun startFlashSaleRecycler() {
        vm.flashSaleProductsLD.observe(viewLifecycleOwner) {
            vb.recyclerFlashSaleHome.adapter = flashSaleAdapter
            flashSaleAdapter.flashSaleList = it
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
        vm.getUserPhoto()

        vm.userPhotoLD.observe(viewLifecycleOwner) {
            Glide.with(this@HomeFragment)
                .load(it)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_user_placeholder)
                .into(vb.ivUserPhotoHome)
        }
    }
}