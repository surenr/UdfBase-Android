package com.android.android.udfbase.actions

import com.android.android.udfbase.domain.ApiError
import com.android.android.udfbase.domain.TestUser
import com.android.android.udfbase.state.ActionStatus

sealed class AddTestUser(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Request(val name: String, val age: Int, val email: String, actionId: String?): AddTestUser(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Perform(val testUser: TestUser, actionId: String?):  AddTestUser(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  AddTestUser(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
    class Failure(actionError: ApiError, actionId: String?): AddTestUser(baseId = actionId, actionStatus = ActionStatus.ERROR, error = actionError)
}

sealed class RemoveTestUser(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val email: String, actionId: String?):  RemoveTestUser(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  RemoveTestUser(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
    class Failure(actionError: ApiError, actionId: String?): RemoveTestUser(baseId = actionId, actionStatus = ActionStatus.ERROR, error = actionError)
}

sealed class RemoveAllUsers(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(actionId: String?):  RemoveAllUsers(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  RemoveAllUsers(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
    class Failure(actionError: ApiError, actionId: String?): RemoveAllUsers(baseId = actionId, actionStatus = ActionStatus.ERROR, error = actionError)
}