package com.test.zap.interfaces

import android.support.v4.app.FragmentActivity

/**
 * Created by srikanth on 15/06/2018.
 */
interface LifeCycle {

    interface View {
        val parentActivity: FragmentActivity
    }

    interface ViewModel {
        fun onViewResumed()
        fun onViewAttached(viewCallback: LifeCycle.View)
        fun onViewDetached()
    }
}