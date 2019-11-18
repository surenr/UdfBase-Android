package com.android.android.udfbase.base

import com.android.android.udfbase.actions.BaseAction
import com.android.android.udfbase.actions.RemoveStateStatus
import com.android.android.udfbase.state.ActionStatus
import com.android.android.udfbase.state.UdfBaseState
import com.android.android.udfbase.state.getStateFlowStatusBySession
import org.rekotlin.Store

import org.rekotlin.StoreSubscriber
import java.util.*

interface  Base <T>: StoreSubscriber<UdfBaseState<T>> {
    val appStore: Store<UdfBaseState<T>>
    var actionSessionIds: ArrayList<String>

    fun getActionId(): String {
        val actionId =  UUID.randomUUID().toString()
        actionSessionIds.add(actionId)
        return actionId
    }

    fun dispatchAction(action: BaseAction, showLoader: Boolean = true) {
        if(showLoader)
            showLoader()
        appStore.dispatch(action)
    }

    fun showLoader()

    fun hideLoader()

    override fun newState(state: UdfBaseState<T>) {
        this.onRawStateUpdate(state)
        val ids: ArrayList<String> = this.actionSessionIds.clone() as ArrayList<String>
        ids.forEach { updateActivity(state, it) }
    }

    private fun updateActivity(state: UdfBaseState<T>, actionId: String) {
        getStateFlowStatusBySession(state, actionId)?.let { action ->
            appStore.dispatch(RemoveStateStatus.Perform(actionId, getActionId()))
            if (action.status == ActionStatus.COMPLETED) {
                if (onStateUpdate(state, action))
                    hideLoader()
            } else if (action.status == ActionStatus.ERROR) {
                onError(action)
                hideLoader()
            }
        }
    }

    fun onStateUpdate(state: UdfBaseState<T>, action: BaseAction): Boolean
    fun onRawStateUpdate(state: UdfBaseState<T>)
    fun onError(action: BaseAction)
}