package com.android.android.udfbase.reducers

import com.android.android.udfbase.actions.AddTestUser
import com.android.android.udfbase.actions.RemoveAllUsers
import com.android.android.udfbase.actions.RemoveTestUser
import com.android.android.udfbase.state.TestState
import com.android.android.udfbase.state.UdfBaseState
import com.android.android.udfbase.state.getAllUsers
import org.rekotlin.Action

val reducerHandler: ReducerHandler<TestState> = {action: Action, state: UdfBaseState<TestState> ->
     when(action) {
          is AddTestUser -> {
                addNewTestUserReducer(action, state)
          }
          is RemoveTestUser -> {
               removeTestUserReducer(action, state)
          }
          is RemoveAllUsers -> {
               removeAllTestUsersReducer(action, state)
          }
          else -> state
     }
}

fun addNewTestUserReducer(
     action: Action,
     state: UdfBaseState<TestState>): UdfBaseState<TestState> {

     when(action as AddTestUser) {
          is AddTestUser.Perform -> {
               val data = action as AddTestUser.Perform
               val newUsersList = getAllUsers(state).toMutableList()
               newUsersList.add(data.testUser)
               val newAppState = state.state.copy(users = newUsersList, count = newUsersList.count())
               val localState = state.copy(state = newAppState)
               return updateActionsStateStatus(localState, action.getId(), AddTestUser.Success(action.getId()!!))
          }
          is AddTestUser.Failure -> {
               val data = action as AddTestUser.Failure
               return updateActionsStateStatus(state, action.getId(), AddTestUser.Failure(data.error!!, action.getId()!!))
          }
     }
     return state
}

fun removeTestUserReducer(
     action: Action,
     state: UdfBaseState<TestState>): UdfBaseState<TestState> {

     when(action as RemoveTestUser) {
          is RemoveTestUser.Perform -> {
               val data = action as RemoveTestUser.Perform
               val newUsersList = getAllUsers(state).filter { user -> user.email != data.email }
               val newAppState = state.state.copy(users = newUsersList, count = newUsersList.count())
               val localState = state.copy(state = newAppState)
               return updateActionsStateStatus(localState, action.getId(), RemoveTestUser.Success(action.getId()!!))
          }
          is RemoveTestUser.Failure -> {
               val data = action as RemoveTestUser.Failure
               return updateActionsStateStatus(state, action.getId(), RemoveTestUser.Failure(data.error!!, action.getId()!!))
          }
     }
     return state
}

fun removeAllTestUsersReducer(
     action: Action,
     state: UdfBaseState<TestState>): UdfBaseState<TestState> {

     when(action as RemoveAllUsers) {
          is RemoveAllUsers.Perform -> {
               val newAppState = state.state.copy(users = listOf(), count = 0)
               val localState = state.copy(state = newAppState)
               return updateActionsStateStatus(localState, action.getId(), RemoveAllUsers.Success(action.getId()!!))
          }
          is RemoveAllUsers.Failure -> {
               val data = action as RemoveAllUsers.Failure
               return updateActionsStateStatus(state, action.getId(), RemoveAllUsers.Failure(data.error!!, action.getId()!!))
          }
     }
     return state
}
