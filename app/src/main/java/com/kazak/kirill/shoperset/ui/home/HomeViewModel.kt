package com.kazak.kirill.shoperset.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserPhotoUseCase
import com.kazak.kirill.shoperset.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestModel
import com.kazak.kirill.shoperset.domain.products.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.shoperset.domain.products.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.shoperset.domain.products.model.product.ProductModel
import com.kazak.kirill.shoperset.domain.products.useCase.GetProductInformationUseCase
import com.kazak.kirill.shoperset.domain.searchingHint.model.SearchingHintModel
import com.kazak.kirill.shoperset.domain.searchingHint.useCase.GetSearchingHintsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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
    var hintsLD = MutableLiveData<SearchingHintModel>()

    val categoriesNameList = listOf("Phones", "Headphones", "Games", "Cars", "Furniture", "Kids")

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getLatestSearchProducts(activeCategories: List<String>) {
        compositeDisposable.add(
            getLatestSearchProductUseCase.getLatestSearchProduct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({allList ->
                    latestSearchProductsLD.postValue(allList.latest.filter { activeCategories.contains(it.category) })
                }, { })
        )
    }

    fun getSplashSaleProducts(activeCategories: List<String>) {
        compositeDisposable.add(
            getFlashSaleProductsUseCase.getFlashSaleProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ allList ->
                    val toSend = allList.flash_sale.filter { activeCategories.contains(it.category) }
                    flashSaleProductsLD.postValue(toSend)
                }, { })
        )
    }

    fun getInformationAboutProduct() {
        compositeDisposable.add(
            getProductInformationUseCase.getProductInformation()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    productInformationLD.postValue(it)
                }, { })
        )
    }

    fun getSearchingHints() {
        compositeDisposable.add(
            getSearchingHintsUseCase.getSearchingHints()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hintsLD.value = it
                }, { })
        )
    }

    fun getUserPhoto() = getUserPhotoUseCase.getUserPhoto()
}