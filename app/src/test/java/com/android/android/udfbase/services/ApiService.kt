package com.android.android.udfbase.services

import com.android.android.udfbase.domain.TestUser

class ApiService {
    fun addNewUser(name: String, age: Int, email: String): TestUser {
        return TestUser(name, age, email)
    }
}