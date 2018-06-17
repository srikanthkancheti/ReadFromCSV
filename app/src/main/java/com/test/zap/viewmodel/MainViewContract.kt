package com.test.zap.viewmodel

import com.test.zap.interfaces.LifeCycle
import com.test.zap.model.RestaurantModel
import java.util.ArrayList

/**
 * Created by srikanth on 15/06/2018.
 */
interface MainViewContract {

    interface View : LifeCycle.View {
        fun bindViews(view: android.view.View)
        fun bindDataToViews(mRestaurantsList: ArrayList<RestaurantModel>)
    }

    interface ViewModel : LifeCycle.ViewModel {
        fun getRestaurantsList()
    }
}
