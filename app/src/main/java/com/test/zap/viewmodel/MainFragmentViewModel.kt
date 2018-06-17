package com.test.zap.viewmodel

import com.opencsv.CSVReader
import com.test.zap.interfaces.LifeCycle
import com.test.zap.model.RestaurantModel
import com.test.zap.utils.TimingsFormat
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by srikanth on 15/06/2018.
 */
class MainFragmentViewModel : BaseViewModel(), MainViewContract.ViewModel {

    private var viewCallback: MainViewContract.View? = null
    var mRestaurantsList: ArrayList<RestaurantModel> = ArrayList()


    override fun onViewAttached(viewCallback: LifeCycle.View) {
        this.viewCallback = viewCallback as MainViewContract.View
    }

    override fun onViewDetached() {
        this.viewCallback = null
    }

    override fun onViewResumed() {
        if (mRestaurantsList.size == 0)
            getRestaurantsList()
    }

    override fun getRestaurantsList() {
        showProgress(viewCallback)

        mRestaurantsList = readCsvFileFromLocal("take_home_test2.csv")

        for (i in mRestaurantsList.indices) {
            val timings: String = TimingsFormat.convertTimingsString(mRestaurantsList[i])
            mRestaurantsList.get(i).timings = timings
        }

        viewCallback!!.bindDataToViews(mRestaurantsList)

        hideProgress()
    }

    private fun readCsvFileFromLocal(fileName: String): ArrayList<RestaurantModel> {
        val restaurantsList = ArrayList<RestaurantModel>()
        var fileReader: BufferedReader? = null
        var csvReader: CSVReader? = null
        try {
            val `is` = viewCallback!!.parentActivity.assets.open(fileName)
            fileReader = BufferedReader(InputStreamReader(`is`))
            csvReader = CSVReader(fileReader)

            val restaurants = csvReader.readAll()

            for (restaurant in restaurants) {
                var restaurantModel = RestaurantModel()
                restaurantModel.restaurantName = restaurant[0]
                restaurantModel.timings = restaurant[1]
                restaurantsList.add(restaurantModel)
            }
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
                csvReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader/csvParser Error!")
                e.printStackTrace()
            }
        }

        return restaurantsList

    }
}

