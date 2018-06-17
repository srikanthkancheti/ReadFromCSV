package com.test.zap.fragment

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.zap.R
import com.test.zap.adapter.RestaurantsAdapter
import com.test.zap.interfaces.LifeCycle
import com.test.zap.model.RestaurantModel
import com.test.zap.viewmodel.MainFragmentViewModel
import com.test.zap.viewmodel.MainViewContract
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*

/**
 * Created by srikanth on 15/06/2018.
 */
class MainFragment : BaseFragment(), MainViewContract.View {

    private lateinit var mViewModel: MainFragmentViewModel

    fun newInstance(): MainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = MainFragmentViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_main, container,
                false)
        bindViews(view)

        return view
    }

    override fun onBackButtonPressed() {
        activity.onBackPressed()
    }

    override fun getViewModel(): LifeCycle.ViewModel {
        return mViewModel
    }

    override val parentActivity: FragmentActivity
        get() = activity

    override fun bindViews(view: View) {

        view.contentRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun bindDataToViews(mRestaurantsList: ArrayList<RestaurantModel>) {
        view!!.contentRecyclerView.adapter = RestaurantsAdapter().setModelList(mRestaurantsList, activity)
    }

}
