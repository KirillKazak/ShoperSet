package com.kazak.kirill.shoperset.ui.fragments.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentProductBinding
import com.kazak.kirill.shoperset.ui.fragments.Constants.BUNDLE_PRODUCT_KEY
import com.kazak.kirill.shoperset.ui.fragments.Constants.DEFAULT_POSITION_ACTIVE_PHOTO_ITEM
import com.kazak.kirill.shoperset.ui.fragments.Constants.DOLLARS
import com.kazak.kirill.shoperset.ui.fragments.product.additionalPhoto.AdditionalPhotoProductAdapter
import com.kazak.kirill.shoperset.ui.fragments.product.color.ColorProductAdapter
import com.kazak.kirill.shoperset.ui.fragments.product.mainPhoto.MainPhotoProductAdapter
import com.kazak.kirill.shoperset.ui.objects.AdditionalPhotosProductModel
import com.kazak.kirill.shoperset.ui.objects.ColorModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment(R.layout.fragment_product) {
    private val vb: FragmentProductBinding by viewBinding()
    private val vm by viewModel<ProductViewModel>()

    private val additionalPhotoProductAdapter by lazy { AdditionalPhotoProductAdapter() }
    private val colorProductAdapter by lazy { ColorProductAdapter() }
    private val mainPhotoProductAdapter by lazy { MainPhotoProductAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            vm.informationAboutProductLD.value =
                it.getParcelable(BUNDLE_PRODUCT_KEY)
        }

        observeDataAboutProduct()
        observePurchaseQuantityLD()

        btnMinusQuantity()
        btnPlusQuantity()

        onBtnArrowBackClick()
    }

    @SuppressLint("SetTextI18n")
    private fun observeDataAboutProduct() {
        vm.informationAboutProductLD.observe(viewLifecycleOwner) {
            with(vb) {

                tvNameProduct.text = it.name ?: ""
                tvPriceProduct.text = DOLLARS + (it.price.toString())
                tvDescriptionProduct.text = it.description ?: ""
                tvPopularityMarkProduct.text = it.rating.toString()
                tvReviewsProduct.text =
                    "(${it.number_of_reviews} ${requireContext().getString(R.string.reviews)})"

                vm.setProductPriceToVM(it.price)

                startAdditionalPhotoProductAdapter(it.image_urls)
                startMainPhotoViewPager(it.image_urls)
                startColorProductAdapter(it.colors)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observePurchaseQuantityLD() {
        vm.purchaseQuantityLD.observe(viewLifecycleOwner) {
            with(vb) {
                tvQuantityProduct.text = it.quantity.toString()
                tvTotalPriceProduct.text = "$DOLLARS${it.price}"
            }
        }
    }

    private fun startAdditionalPhotoProductAdapter(images: ArrayList<String>?) {
        setPhotosListToPhotoAdapter(images, DEFAULT_POSITION_ACTIVE_PHOTO_ITEM)

        vb.recyclerAdditionalPhotoProduct.adapter = additionalPhotoProductAdapter

        additionalPhotoProductAdapter.onPhotoItemClickListener =
            object : AdditionalPhotoProductAdapter.OnPhotoItemClickListener {

                override fun onPhotoItemClick(id: Int) {
                    vb.vp2MainPhotoProduct.currentItem = id
                }
            }
    }

    private fun startMainPhotoViewPager(images: ArrayList<String>?) {
        setDefaultPhotosListToMainPhotoAdapter(images)
        vb.vp2MainPhotoProduct.adapter = mainPhotoProductAdapter

        vb.vp2MainPhotoProduct.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setPhotosListToPhotoAdapter(images, position)
            }
        })
    }

    private fun startColorProductAdapter(colors: ArrayList<String>?) {
        setDefaultColorsListToColorsAdapter(colors)

        vb.recyclerColorProduct.adapter = colorProductAdapter

        colorProductAdapter.onColorItemClickListener =
            object : ColorProductAdapter.OnColorItemClickListener {
                override fun onColorItemClick(color: String) {}
            }
    }

    private fun setPhotosListToPhotoAdapter(images: ArrayList<String>?, position: Int) {
        images?.let { list ->
            val defaultAdditionalPhotoList = arrayListOf<AdditionalPhotosProductModel>()

            for (i in list) {
                defaultAdditionalPhotoList.add(
                    AdditionalPhotosProductModel(
                        list.indexOf(i) ,i, false
                    )
                )
            }

            defaultAdditionalPhotoList[position].isSelected = true
            additionalPhotoProductAdapter.updateData(defaultAdditionalPhotoList)
        }
    }

    private fun setDefaultPhotosListToMainPhotoAdapter(images: ArrayList<String>?) {
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
            mainPhotoProductAdapter.updateData(defaultAdditionalPhotoList)
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

    private fun btnMinusQuantity() {
        vb.btnMinusQuantityProduct.setOnClickListener {
            vm.decreaseQuantity()
        }
    }

    private fun btnPlusQuantity() {
        vb.btnPlusQuantityProduct.setOnClickListener {
            vm.increaseQuantity()
        }
    }

    private fun onBtnArrowBackClick() {
        vb.ivArrowBackProduct.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}