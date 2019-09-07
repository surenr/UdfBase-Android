package com.android.android.udfbase.middleware

import com.android.android.udfbase.state.UdfBaseState
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

typealias MiddleWareHandler = (DispatchFunction, Action)->Unit

 fun <T>getNetworkMiddleware(handler: MiddleWareHandler): Middleware<UdfBaseState<T>> {
    return { dispatch: DispatchFunction, _ ->
        { next -> {
            action ->
               handler(dispatch, action)
               next(action)
        }}
    }
}