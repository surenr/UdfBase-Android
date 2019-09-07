package com.android.android.udfbase.actions

import com.android.android.udfbase.domain.ApiError
import com.android.android.udfbase.state.ActionStatus
import org.rekotlin.Action

abstract class BaseAction(val id: String? = "", var status: ActionStatus? = ActionStatus.INIT, var error: ApiError? = null):
    Action {
    val getId: ()->String? = { this.id }
    val setStatus: (status: ActionStatus) -> Unit = { status -> this.status = status }
    val getStatus: ()-> ActionStatus = {
        if(this.status != null) {
            this.status
        }
        ActionStatus.INIT
    }

    val setError: (error: ApiError?) -> Unit = { error -> this.error = error }
    val getError: ()-> ApiError? = { this.error }
}

sealed class RemoveStateStatus(baseId: String? = "", actionStatus: ActionStatus? = ActionStatus.INIT, error: ApiError? = null): BaseAction(baseId, actionStatus, error) {
    class Perform(val uuid: String, actionId: String?):  RemoveStateStatus(baseId = actionId, actionStatus = ActionStatus.INIT)
    class Success(actionId: String?):  RemoveStateStatus(baseId = actionId, actionStatus = ActionStatus.COMPLETED)
}