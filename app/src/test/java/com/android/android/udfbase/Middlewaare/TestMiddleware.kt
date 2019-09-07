package com.android.android.udfbase.Middlewaare

import com.android.android.udfbase.Store.testAppStore
import com.android.android.udfbase.actions.AddTestUser
import com.android.android.udfbase.domain.ApiError
import com.android.android.udfbase.middleware.MiddleWareHandler
import com.android.android.udfbase.services.ApiService
import com.android.android.udfbase.state.TestState
import com.android.android.udfbase.state.UdfBaseState
import com.android.android.udfbase.state.getAllUsers
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import java.lang.Exception


fun addNewTestUserMiddleware(dispatch: DispatchFunction, action: AddTestUser.Request) {
    val service = ApiService()

    try {
        val users = getAllUsers(testAppStore.state)

        if (users.firstOrNull{ it.email == action.email } != null ) throw Exception("Duplicate User")

        val testUser = service.addNewUser(action.name, action.age, action.email)
        dispatch(AddTestUser.Perform(testUser, action.getId()))
    }catch(e: Exception) {
        val message = e.message ?: "Unexpected Message"
        dispatch(AddTestUser.Failure(ApiError(606, message), action.getId()))
    }

}

val middlewareHandler: MiddleWareHandler= { dispatch: DispatchFunction, action: Action ->
    when(action) {
        is AddTestUser.Request -> {
            addNewTestUserMiddleware(dispatch, action)
        }
    }
}
