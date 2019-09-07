package com.android.android.udfbase.testUtils

import com.android.android.udfbase.domain.ApiError
import junit.framework.Assert.assertEquals
import org.awaitility.Awaitility.await
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

 class TestUtils<T, S>{
     fun assertAsyncSuccess(
        target: T,
        expected: S
    ) {
        await().atMost(5, TimeUnit.SECONDS).until {
            target == expected
        }
         assertEquals(expected, target)
    }

     fun assertAsyncError(target: ApiError, expected: ApiError) {
        await().until { target != null }
         assertEquals(expected.code, target.code)
        assertEquals(expected.message, target.message)
    }

}