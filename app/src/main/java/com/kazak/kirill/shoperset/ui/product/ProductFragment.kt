package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentProductBinding
import com.kazak.kirill.shoperset.domain.AdditionalPhotosProductModel
import com.kazak.kirill.shoperset.domain.product.ColorModel
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
            vm.informationAboutProductLD.value =
                it.getParcelable(Constants.BUNDLE_PRODUCT_KEY)
        }

        observeDataAboutProduct()
    }

    @SuppressLint("SetTextI18n")
    private fun observeDataAboutProduct() {
        vm.informationAboutProductLD.observe(viewLifecycleOwner) {
            with(vb) {

                Glide.with(this@ProductFragment)
                    .load(it.image_urls?.get(0))
                    .into(ivMainPhotoProduct)

                tvNameProduct.text = it.name ?: ""
                tvPriceProduct.text = "$ " + (it.price.toString())
                tvDescriptionProduct.text = it.description ?: ""
                tvPopularityMarkProduct.text = it.rating.toString()
                tvReviewsProduct.text = "(${it.number_of_reviews} reviews)"
                tvQuantityProduct.text = quantity.toString()
                tvTotalPriceProduct.text = "$ $totalPrice"

                startAdditionalPhotoProductAdapter(it.image_urls)
                startColorProductAdapter(it.colors)
            }
        }
    }

    private fun startAdditionalPhotoProductAdapter(images: ArrayList<String>?) {
        setDefaultPhotosListToPhotoAdapter(images)

        vb.recyclerAdditionalPhotoProduct.adapter = additionalPhotoProductAdapter

        additionalPhotoProductAdapter.onPhotoItemClickListener =
            object : AdditionalPhotoProductAdapter.OnPhotoItemClickListener {
                override fun onPhotoItemClick(imgUrl: String) {
                    Glide.with(this@ProductFragment)
                        .load(imgUrl)
                        .into(vb.ivMainPhotoProduct)
                }

            }
    }

    private fun startColorProductAdapter(colors: ArrayList<String>?) {
        setDefaultColorsListToColorsAdapter(colors)

        vb.recyclerColorProduct.adapter = colorProductAdapter

        colorProductAdapter.onColorItemClickListener =
            object : ColorProductAdapter.OnColorItemClickListener {
                override fun onColorItemClick(color: String) {
                    // TODO: (GET DATA ABOUT PRODUCT WITH SELECTED COLOR AND UPDATE THE SCREEN)
                }
            }
    }

    private fun setDefaultPhotosListToPhotoAdapter(images: ArrayList<String>?) {
        images?.let { list ->
            val defaultAdditionalPhotoList = arrayListOf<AdditionalPhotosProductModel>()

            for (i in list) {
                defaultAdditionalPhotoList.add(
                    AdditionalPhotosProductModel(
                        list.indexOf(i) ,i, false
                    )
                )
            }

            defaultAdditionalPhotoList[0].isSelected = true
            additionalPhotoProductAdapter.updateData(defaultAdditionalPhotoList)
        }
    }

    private fun setDefaultColorsListToColorsAdapter(colors: ArrayList<String>?) {
        colors?.let { colorList ->
            val defaultColorList = arrayListOf<ColorModel>()

            for (i in colorList) {
                defaultColorList.add(
                    ColorModel(
                        colorList.indexOf(i), i, false
                    )
                )
            }

            defaultColorList[0].isSelected = true
            colorProductAdapter.updateData(defaultColorList)
        }
    }
}