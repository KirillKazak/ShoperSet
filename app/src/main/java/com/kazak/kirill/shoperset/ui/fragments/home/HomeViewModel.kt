package com.kazak.kirill.shoperset.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazak.kirill.shoperset.ui.objects.ProductsModel
import com.kazak.kirill.domain.credentials.useCase.GetUserPhotoUseCase
import com.kazak.kirill.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.domain.products.model.latestSearch.LatestModel
import com.kazak.kirill.domain.products.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.domain.productInformation.model.product.ProductModel
import com.kazak.kirill.domain.productInformation.useCase.GetProductInformationUseCase
import com.kazak.kirill.domain.products.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.domain.searchingHint.model.SearchingHintModel
import com.kazak.kirill.domain.searchingHint.useCase.GetSearchingHintsUseCase
import com.kazak.kirill.shoperset.ui.provideExceptionHandler
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
        viewModelScope.launch(provideExceptionHandler()) {
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
        viewModelScope.launch(provideExceptionHandler()) {
            val result = withContext(Dispatchers.IO) {
                return@withContext getProductInformationUseCase.getProductInformation()
            }

            productInformationLD.postValue(result)
        }
    }

    fun getSearchingHints() {
        viewModelScope.launch(provideExceptionHandler()) {
            val result = withContext(Dispatchers.IO) {
                return@withContext getSearchingHintsUseCase.getSearchingHints()
            }

            hintsLD.postValue(result)
        }
    }

    fun getUserPhoto() {
        viewModelScope.launch(provideExceptionHandler()) {
            val result = withContext(Dispatchers.IO) {
                return@withContext getUserPhotoUseCase.getUserPhoto()
            }
            userPhotoLD.postValue(result)
        }
    }
}