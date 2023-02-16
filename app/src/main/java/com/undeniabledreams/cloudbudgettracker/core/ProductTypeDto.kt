package com.undeniabledreams.cloudbudgettracker.core

class ProductTypeDto {

    private var productTypeName: String

    constructor(productTypeName: String) {
        this.productTypeName = productTypeName
    }

    constructor() {
        this.productTypeName = ""
    }

    fun getProductTypeName(): String {
        return productTypeName
    }

    fun setProductTypeName(productType: String) {
        this.productTypeName = productType
    }

}