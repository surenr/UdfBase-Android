package com.android.android.udfbase.base

import com.android.android.udfbase.Store.testAppStore
import com.android.android.udfbase.state.TestState
import com.android.android.udfbase.state.UdfBaseState
import org.rekotlin.Store
import java.util.ArrayList

 open abstract class TestBase : Base<TestState> {
     override val appStore: Store<UdfBaseState<TestState>> = testAppStore
     override var actionSessionIds: ArrayList<String> = ArrayList()

     override fun showLoader() {
         // Do nothing for test test base class
     }

     override fun hideLoader() {
         // Do nothing for test base class
     }
}