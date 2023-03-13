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
import com.kazak.kirill.shoperset.util.Constants.CARS
import com.kazak.kirill.shoperset.util.Constants.FURNITURE
import com.kazak.kirill.shoperset.util.Constants.GAMES
import com.kazak.kirill.shoperset.util.Constants.HEADPHONES
import com.kazak.kirill.shoperset.util.Constants.KIDS
import com.kazak.kirill.shoperset.util.Constants.PHONES
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class HomeViewModel(
    private val getLatestSearchProductUseCase: GetLatestSearchProductUseCase,
    private val getFlashSaleProductsUseCase: GetFlashSaleProductsUseCase,
    private val getProductInformationUseCase: GetProductInformationUseCase,
    private val getSearchingHintsUseCase: GetSearchingHintsUseCase,
    private val getUserPhotoUseCase: GetUserPhotoUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val latestSearchProductsLD = MutableLiveData<List<LatestModel>>()
    val flashSaleProductsLD = MutableLiveData<List<FlashSale>>()
    val productInformationLD = MutableLiveData<ProductModel>()
    val userPhotoLD = MutableLiveData<String>()
    var hintsLD = MutableLiveData<SearchingHintModel>()

    val categoriesNameList = listOf(PHONES, HEADPHONES, GAMES, CARS, FURNITURE, KIDS)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getProducts(activeCategories: List<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {

                val latestSearchProducts = async {
                    return@async getLatestSearchProductUseCase.getLatestSearchProduct()
                        .latest.filter { activeCategories.contains(it.category) }
                }

                val splashSaleProducts = async {
                    return@async getFlashSaleProductsUseCase.getFlashSaleProducts()
                        .flash_sale.filter { activeCategories.contains(it.category) }
                }

                return@withContext ProductsModel(
                    latestSearchProducts.await(),
                    splashSaleProducts.await()
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