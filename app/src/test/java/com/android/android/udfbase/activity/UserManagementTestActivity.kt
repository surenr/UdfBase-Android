package com.android.android.udfbase.activity

import com.android.android.udfbase.Store.testAppStore
import com.android.android.udfbase.actions.AddTestUser
import com.android.android.udfbase.actions.BaseAction
import com.android.android.udfbase.actions.RemoveAllUsers
import com.android.android.udfbase.actions.RemoveTestUser
import com.android.android.udfbase.base.TestBase
import com.android.android.udfbase.domain.ApiError
import com.android.android.udfbase.domain.TestUser
import com.android.android.udfbase.state.TestState
import com.android.android.udfbase.state.UdfBaseState
import com.android.android.udfbase.state.getAllUsers
import com.android.android.udfbase.state.getUserCount

class UserManagementTestActivity(var users: List<TestUser> = getAllUsers(testAppStore.state),
                                 var count: Int = getUserCount(
    testAppStore.state)): TestBase() {
    var error: ApiError? = null
    override fun onStateUpdate(state: UdfBaseState<TestState>, action: BaseAction): Boolean {
        when(action!!) { // NB: How to handle action based state update
            is AddTestUser -> {
                this.users = getAllUsers(state)
                this.count = getUserCount(state)
                return true
            }
            is RemoveTestUser -> {
                this.users = getAllUsers(state)
                this.count = getUserCount(state)
                return true
            }
        }
        return false
    }

    override fun onError(action: BaseAction) {
        this.error = action.getError()
    }

    fun addUser(name: String, age: Int, email: String) {
        dispatchAction(AddTestUser.Request(name, age,email, getActionId()))
    }

    fun removeAll() {
        dispatchAction(RemoveAllUsers.Perform(getActionId()))
    }

    fun removeUser(email: String) {
        dispatchAction(RemoveTestUser.Perform(email, getActionId()))
    }

}