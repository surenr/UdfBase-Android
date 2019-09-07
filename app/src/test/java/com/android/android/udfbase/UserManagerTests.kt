package com.android.android.udfbase

import com.android.android.udfbase.Store.testAppStore
import com.android.android.udfbase.actions.AddTestUser
import com.android.android.udfbase.activity.UserManagementTestActivity
import com.android.android.udfbase.domain.ApiError
import com.android.android.udfbase.testUtils.TestUtils
import org.junit.*

import org.junit.Assert.*
import org.junit.runners.MethodSorters
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


class UserManagerTests {

    companion object {
        lateinit var userManager: UserManagementTestActivity
        init {
            // things that may need to be setup before companion class member variables are instantiated
        }

        @BeforeClass @JvmStatic fun setup() {
            userManager = UserManagementTestActivity()
            testAppStore.subscribe(userManager)
        }

        @AfterClass @JvmStatic fun teardown() {
            testAppStore.unsubscribe(userManager)
        }
    }



    @Before
    fun beforeTest() {

        userManager.addUser("Suren Rodrigo", 38, "surenr@99x.lk")
        userManager.addUser("Dinuka Rodrigo", 38, "dinuka@99x.lk")
    }

    @After
    fun afterTest() {
        userManager.removeAll()

    }

    @Test
    fun addNewUserTest() {
        val testUtil = TestUtils<Int, Int>()
        assertEquals(2, userManager.count)
        userManager.addUser("Kamal", 54, "kamal@99x.lk")
        testUtil.assertAsyncSuccess(userManager.count, 3)

    }

    @Test
    fun removeUserTest() {
        val testUtils = TestUtils<Int, Int>()
        assertEquals(2, userManager.count)
        userManager.removeUser("surenr@99x.lk")
        testUtils.assertAsyncSuccess(userManager.count, 1)
    }

    @Test
    fun addingExistingUserThrowError() {
        val testUtils = TestUtils<ApiError, ApiError>()
        assertEquals(2, userManager.count)
        userManager.addUser("Suren Rodrigo", 38, "surenr@99x.lk")
        testUtils.assertAsyncError(userManager.error!!, ApiError(606, "Duplicate User"))
    }
}
