package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentProductBinding
import com.kazak.kirill.shoperset.util.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

const val DEFAULT_QUANTITY_VALUE = 0
const val DEFAULT_TOTAL_PRICE_VALUE = 0
class ProductFragment : Fragment(R.layout.fragment_product) {
    private val vb: FragmentProductBinding by viewBinding()
    private val vm by viewModel<ProductViewModel>()
    private val additionalPhotoProductAdapter by lazy { AdditionalPhotoProductAdapter() }
    private val colorProductAdapter by lazy { ColorProductAdapter() }


    private var quantity = DEFAULT_QUANTITY_VALUE
    private var totalPrice = DEFAULT_TOTAL_PRICE_VALUE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            vm.informationAboutModel =
                it.getParcelable(Constants.BUNDLE_PRODUCT_KEY)
        }

        setData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        with(vb) {

            Glide.with(this@ProductFragment)
                .load(vm.informationAboutModel?.image_urls?.get(0))
                .into(ivMainPhotoProduct)

            tvNameProduct.text = vm.informationAboutModel?.name ?: ""
            tvPriceProduct.text = "$ " + (vm.informationAboutModel?.price.toString())
            tvDescriptionProduct.text = vm.informationAboutModel?.description ?: ""
            tvPopularityMarkProduct.text = vm.informationAboutModel?.rating.toString()
            tvReviewsProduct.text = "(${vm.informationAboutModel?.number_of_reviews} reviews)"
            tvQuantityProduct.text = quantity.toString()
            tvTotalPriceProduct.text = "$ $totalPrice"

            startAdditionalPhotoProductAdapter()
            startColorProductAdapter()
        }
    }

    private fun startAdditionalPhotoProductAdapter() {
        vm.informationAboutModel?.image_urls?.let {
            additionalPhotoProductAdapter.additionalPhotoList = it
        }
        vb.recyclerAdditionalPhotoProduct.adapter = additionalPhotoProductAdapter
    }

    private fun startColorProductAdapter() {
        vm.informationAboutModel?.colors?.let {
            colorProductAdapter.colorsProductList = it
        }
        vb.recyclerColorProduct.adapter = colorProductAdapter
    }
}