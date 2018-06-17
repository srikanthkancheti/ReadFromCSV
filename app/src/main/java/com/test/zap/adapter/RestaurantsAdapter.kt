package com.test.zap.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.zap.R
import com.test.zap.model.RestaurantModel
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

/**
 * Created by srikanth on 16/06/2018.
 */
class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    private var context: Context? = null
    private lateinit var mRestaurantsList: ArrayList<RestaurantModel>

    fun setModelList(modelList: ArrayList<RestaurantModel>, context: Activity): RestaurantsAdapter {
        mRestaurantsList = modelList
        this.context = context
        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_list_item, parent, false))

    override fun onBindViewHolder(holder: RestaurantsAdapter.ViewHolder, position: Int) {
        holder.bindItems(mRestaurantsList.get(position))
    }

    override fun getItemCount(): Int = mRestaurantsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(restaurantModel: RestaurantModel) {
            itemView.tvRestaurantName.text = restaurantModel.restaurantName
            itemView.tvRestaurantTimings.text = restaurantModel.timings
        }
    }
}