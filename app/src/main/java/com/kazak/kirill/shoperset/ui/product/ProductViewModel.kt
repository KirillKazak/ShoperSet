package com.kazak.kirill.shoperset.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.PurchaseQuantityModel
import com.kazak.kirill.shoperset.domain.product.model.ProductModel

const val DEFAULT_QUANTITY_VALUE = 0
const val DEFAULT_PRICE_VALUE = 0
class ProductViewModel(): ViewModel() {

    val informationAboutProductLD = MutableLiveData<ProductModel>()
    val purchaseQuantityLD = MutableLiveData<PurchaseQuantityModel>()

    private var quantityProduct = DEFAULT_QUANTITY_VALUE
    private var priceProduct = DEFAULT_PRICE_VALUE

    init {
        sendPurchaseQuantity()
    }
    private fun sendPurchaseQuantity() {
        purchaseQuantityLD.value = PurchaseQuantityModel(
            quantityProduct,
            priceProduct * quantityProduct
        )
    }

    fun setProductPriceToVM(price: Int) {
        priceProduct = price
    }

    fun increaseQuantity() {
        quantityProduct += 1
        sendPurchaseQuantity()
    }

    fun decreaseQuantity() {
        if (quantityProduct > 0) {
            quantityProduct -= 1
            sendPurchaseQuantity()
        }
    }

}