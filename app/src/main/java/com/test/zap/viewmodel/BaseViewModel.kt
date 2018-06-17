package com.test.zap.viewmodel

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.VISIBLE
import android.view.Window
import com.test.zap.R
import com.test.zap.interfaces.LifeCycle
import kotlinx.android.synthetic.main.view_progress_dialog.view.*

/**
 * Created by srikanth on 15/06/2018.
 */
open class BaseViewModel {

    private var progressDialog: Dialog? = null

    fun showProgress(viewCallBack: LifeCycle.View?) {
        if (progressDialog == null && viewCallBack != null) {
            progressDialog = Dialog(viewCallBack.parentActivity)
            progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val inflater = viewCallBack.parentActivity.getLayoutInflater()
            val layout = inflater.inflate(R.layout.view_progress_dialog, null)
            layout.progressBar.visibility = VISIBLE
//            val progress = layout.findViewById(R.id.progressBar)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.show()
        } else if (progressDialog != null && viewCallBack != null) {
            progressDialog!!.show()
        }
    }

    fun hideProgress() {
        if (progressDialog != null) {
            progressDialog!!.cancel()
            progressDialog = null
        }
    }
}

