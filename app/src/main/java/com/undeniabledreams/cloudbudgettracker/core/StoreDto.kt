package com.undeniabledreams.cloudbudgettracker.core

class StoreDto {

    private var storeName: String

    constructor(storeName: String) {
        this.storeName = storeName
    }

    constructor() {
        this.storeName = ""
    }

    fun getStoreName(): String {
        return storeName
    }

    fun setStoreName(storeName: String) {
        this.storeName = storeName
    }

}