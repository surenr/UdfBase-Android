package com.android.android.udfbase.reducers


import com.android.android.udfbase.actions.BaseAction
import com.android.android.udfbase.actions.RemoveStateStatus
import com.android.android.udfbase.state.UdfBaseState
import org.rekotlin.Action
import org.rekotlin.Reducer

typealias ReducerHandler<T> = (Action, UdfBaseState<T>) -> UdfBaseState<T>
internal typealias ReducerType<T> = (Action, UdfBaseState<T>?) -> UdfBaseState<T>

fun <T> updateActionsStateStatus(state: UdfBaseState<T>, actionId: String?, action: BaseAction): UdfBaseState<T> {
    if(actionId != null) {
        val statusMap = state.systemStateUpdateTracker
            .toMutableMap()
            .filterKeys { it != actionId }
            .toMutableMap()
        statusMap[actionId] = action
        return state.copy(systemStateUpdateTracker = statusMap.toMap())
    }
    return state
}

//fun <T> appReducers(stateInstance: T, action: Action, state: UdfBaseState<T>?, handler: ReducerHandler<T>): UdfBaseState<T> {
//    val appState = state ?: UdfBaseState(stateInstance)
//    return handler(action, appState)
//}

fun <T> getAppReducer(stateInstance: T, handler: ReducerHandler<T>): Reducer<UdfBaseState<T>> {
    return { action, state ->
        when(action) {
            is RemoveStateStatus.Perform -> {
                val statusMap = state!!.systemStateUpdateTracker
                    .toMutableMap()
                    .filterKeys { it != action.uuid }
                state.copy(systemStateUpdateTracker = statusMap)
            }
            else -> handler(action, state ?: UdfBaseState(stateInstance))
        }
    }
}