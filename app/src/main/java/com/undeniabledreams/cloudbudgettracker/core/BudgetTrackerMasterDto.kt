package com.undeniabledreams.cloudbudgettracker.core

import java.util.Date

class BudgetTrackerMasterDto {

    private var date: Date? = null
    private var storeId: Int? = null
    private var productName: String? = ""
    private var productTypeId: Int? = null
    private var price: Double = 0.0
    private var vat: Double = 0.0
    private var currencyId: Int? = null
    private var userName: String? = null

    constructor(
        date: Date?,
        storeId: Int?,
        productName: String?,
        productTypeId: Int?,
        price: Double,
        vat: Double,
        currencyId: Int?,
        userName: String?
    ) {
        this.date = date
        this.storeId = storeId
        this.productName = productName
        this.productTypeId = productTypeId
        this.price = price
        this.vat = vat
        this.currencyId = currencyId
        this.userName = userName
    }

    constructor()


    fun getDate(): Date? {
        return date
    }

    fun setDate(date: Date?) {
        this.date = date
    }

    fun getStoreId(): Int? {
        return storeId
    }

    fun setStoreId(storeId: Int?) {
        this.storeId = storeId
    }

    fun getProductName(): String? {
        return productName
    }

    fun setProductName(productNameId: String?) {
        this.productName = productNameId
    }

    fun getProductTypeId(): Int? {
        return productTypeId
    }

    fun setProductTypeId(productTypeId: Int?) {
        this.productTypeId = productTypeId
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getVat(): Double {
        return vat
    }

    fun setVat(vat: Double) {
        this.vat = vat
    }

    fun getCurrencyId(): Int? {
        return currencyId
    }

    fun setCurrencyId(currencyId: Int?) {
        this.currencyId = currencyId
    }

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

}