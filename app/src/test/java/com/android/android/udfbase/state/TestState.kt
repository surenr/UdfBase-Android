package com.android.android.udfbase.state

import com.android.android.udfbase.domain.TestUser

data class TestState(
    val users: List<TestUser> = listOf(),
    val count: Int = 0
)

val getAllUsers ={ state: UdfBaseState<TestState> -> state.state.users}
val getUserCount = { state: UdfBaseState<TestState> -> state.state.count }