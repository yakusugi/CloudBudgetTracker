package com.undeniabledreams.cloudbudgettracker.core

class CurrencyDto {

    private var currencyName: String = "$"
    private var currencyNameAbbr: String = ""

    constructor(currencyName: String, currencyNameAbbr: String) {
        this.currencyName = currencyName
        this.currencyNameAbbr = currencyNameAbbr
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

    fun getCurrencyAbbr(): String {
        return currencyNameAbbr
    }

    fun setCurrencyAbbr(currencyNameAbbr: String) {
        this.currencyNameAbbr = currencyNameAbbr
    }

}