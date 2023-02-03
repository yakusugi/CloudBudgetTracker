package com.undeniabledreams.cloudbudgettracker.core

class BudgetTrackerUserDto {

    private var id: String
    private var password: String

    constructor(id: String, password: String) {
        this.id = id
        this.password = password
    }

    constructor() {
        this.id = ""
        this.password = ""
    }

    fun getId(): String {
        return id
    }

    fun getPassword(): String {
        return password
    }

    fun setId(id: String) {
        this.id = id
    }

    fun setPassword(password: String) {
        this.password = password
    }

}