package com.android.android.udfbase.Store

import com.android.android.udfbase.Middlewaare.middlewareHandler
import com.android.android.udfbase.middleware.getNetworkMiddleware
import com.android.android.udfbase.reducers.getAppReducer
import com.android.android.udfbase.reducers.reducerHandler
import com.android.android.udfbase.state.TestState
import org.rekotlin.Store

val testAppStore = Store(
    reducer = getAppReducer(TestState(), reducerHandler),
    state = null,
    middleware = listOf(getNetworkMiddleware(middlewareHandler))
)