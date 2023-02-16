package com.undeniabledreams.cloudbudgettracker.core

class CurrencyDto {

    private var currencyName: String

    constructor(currencyName: String) {
        this.currencyName = currencyName
    }

    constructor() {
        this.currencyName = ""
    }

    fun getCurrency(): String {
        return currencyName
    }

    fun setCurrency(currencyName: String) {
        this.currencyName = currencyName
    }

}