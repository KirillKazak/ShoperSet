package com.kazak.kirill.shoperset.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.domain.ProductsModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserPhotoUseCase
import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestModel
import com.kazak.kirill.shoperset.domain.products.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.shoperset.domain.products.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.shoperset.domain.productInformation.model.product.ProductModel
import com.kazak.kirill.shoperset.domain.productInformation.useCase.GetProductInformationUseCase
import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel
import com.kazak.kirill.shoperset.domain.searchingHint.useCase.GetSearchingHintsUseCase
import kotlinx.coroutines.*

class HomeViewModel(
    private val getLatestSearchProductUseCase: GetLatestSearchProductUseCase,
    private val getFlashSaleProductsUseCase: GetFlashSaleProductsUseCase,
    private val getProductInformationUseCase: GetProductInformationUseCase,
    private val getSearchingHintsUseCase: GetSearchingHintsUseCase,
    private val getUserPhotoUseCase: GetUserPhotoUseCase
): ViewModel() {

    val latestSearchProductsLD = MutableLiveData<List<LatestModel>>()
    val flashSaleProductsLD = MutableLiveData<List<FlashSale>>()
    val productInformationLD = MutableLiveData<ProductModel>()
    val userPhotoLD = MutableLiveData<String>()
    var hintsLD = MutableLiveData<SearchingHintModel>()



    fun getProducts(activeCategories: List<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {

                val latestSearchProducts = async {
                    return@async getLatestSearchProductUseCase.getLatestSearchProduct(activeCategories)
                }

                val flashSaleProducts = async {
                    return@async getFlashSaleProductsUseCase.getFlashSaleProducts(activeCategories)
                }

                return@withContext ProductsModel(
                    latestSearchProducts.await(),
                    flashSaleProducts.await()
                )
            }
            latestSearchProductsLD.postValue(result.latestModelList)
            flashSaleProductsLD.postValue(result.flashSaleList)
        }
    }

    fun getInformationAboutProduct() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext getProductInformationUseCase.getProductInformation()
            }

            productInformationLD.postValue(result)
        }
    }

    fun getSearchingHints() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext getSearchingHintsUseCase.getSearchingHints()
            }

            hintsLD.postValue(result)
        }
    }

    fun getUserPhoto() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext getUserPhotoUseCase.getUserPhoto()
            }
            userPhotoLD.postValue(result)
        }
    }
}