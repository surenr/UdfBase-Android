package com.android.android.udfbase.state

import com.android.android.udfbase.actions.BaseAction
import org.rekotlin.StateType


data class UdfBaseState<T> (
    val state: T,
    val systemStateUpdateTracker: Map<String, BaseAction> = hashMapOf()

): StateType


enum class ActionStatus {
    INIT, COMPLETED, ERROR
}


fun <T> getStateFlowStatusBySession(state: UdfBaseState<T>, sessionId: String): BaseAction? {
    return state.systemStateUpdateTracker[sessionId]
}
