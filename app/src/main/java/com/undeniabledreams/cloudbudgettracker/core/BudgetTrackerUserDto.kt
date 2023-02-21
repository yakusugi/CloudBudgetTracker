package com.undeniabledreams.cloudbudgettracker.core

class BudgetTrackerUserDto {

    private lateinit var email: String
    private var id: String
    private var password: String

    constructor(email: String, id: String, password: String) {
        this.email = email
        this.id = id
        this.password = password
    }

    constructor() {
        this.email = ""
        this.id = ""
        this.password = ""
    }

    fun getEmail(): String {
        return email
    }

    fun getId(): String {
        return id
    }

    fun getPassword(): String {
        return password
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setId(id: String) {
        this.id = id
    }

    fun setPassword(password: String) {
        this.password = password
    }

}