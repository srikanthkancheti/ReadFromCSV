package com.test.zap.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.KeyEvent
import android.view.View
import com.test.zap.interfaces.LifeCycle

/**
 * Created by srikanth on 15/06/2018.
 */
abstract class BaseFragment: DialogFragment(), LifeCycle.View, View.OnKeyListener {

    protected abstract fun getViewModel(): LifeCycle.ViewModel
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(this)
    }


    override val parentActivity: FragmentActivity
        get() = activity

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if (event.action == KeyEvent.ACTION_UP) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    onBackButtonPressed()
                    return true
                }
            }
        }

        return false
    }

    abstract fun onBackButtonPressed()

    override fun onResume() {
        super.onResume()
        val viewModel = getViewModel()
        if (true) viewModel.onViewResumed()
    }

    override fun onStart() {
        super.onStart()
        val viewModel = getViewModel()
        viewModel.onViewAttached(this)
    }

    override fun onStop() {
        super.onStop()
        val viewModel = getViewModel()
        if (true) viewModel.onViewDetached()
    }
}