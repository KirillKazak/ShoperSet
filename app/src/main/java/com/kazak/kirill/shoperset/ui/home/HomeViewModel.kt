package com.kazak.kirill.shoperset.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazak.kirill.shoperset.domain.latestSearch.model.flashSale.FlashSaleModel
import com.kazak.kirill.shoperset.domain.latestSearch.model.latestSearch.LatestSearchModel
import com.kazak.kirill.shoperset.domain.latestSearch.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.shoperset.domain.latestSearch.useCase.GetLatestSearchProductUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val getLatestSearchProductUseCase: GetLatestSearchProductUseCase,
    private val getFlashSaleProductsUseCase: GetFlashSaleProductsUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val latestSearchProductsLD = MutableLiveData<LatestSearchModel>()
    val flashSaleProductsLD = MutableLiveData<FlashSaleModel>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getLatestSearchProducts() {
        compositeDisposable.add(
            getLatestSearchProductUseCase.getLatestSearchProduct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    latestSearchProductsLD.postValue(it)
                }, { })
        )
    }

    fun getSplashSaleProducts() {
        compositeDisposable.add(
            getFlashSaleProductsUseCase.getFlashSaleProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    flashSaleProductsLD.postValue(it)
                }, { })
        )
    }
}