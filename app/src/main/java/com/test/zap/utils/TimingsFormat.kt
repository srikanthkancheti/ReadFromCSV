package com.test.zap.utils

import com.test.zap.model.RestaurantModel

import java.util.ArrayList

/**
 * Created by srikanth on 17/06/2018.
 */

object TimingsFormat {

    fun convertTimingsString(restaurantModel: RestaurantModel): String {
        var second: String? = null
        var second2: String? = null
        var firstTimings: String? = null
        var lastTimings: String? = null
        var string2: String? = null
        var string4: String? = null
        var finalWeeksListString = ""
        var lastWeeks: String? = null
        var secondWeek: String? = null
        var string6: String? = null

        val weeksList = ArrayList<String>()
        var firstWeeksList: MutableList<String> = ArrayList()
        var secondWeeksList: MutableList<String> = ArrayList()
        val finalWeeksList = ArrayList<String>()
        var thirdWeeksList: MutableList<String> = ArrayList()
        weeksList.add("Mon")
        weeksList.add("Tue")
        weeksList.add("Wed")
        weeksList.add("Thu")
        weeksList.add("Fri")
        weeksList.add("Sat")
        weeksList.add("Sun")
        val tokens = restaurantModel.timings.split("/".toRegex(), 2).toTypedArray()
        val first = tokens[0].trim { it <= ' ' }
        if (tokens.size > 1)
            second = tokens[1].trim { it <= ' ' }

        val tokens2 = first.split(",".toRegex(), 2).toTypedArray()
        val firstWeeks = tokens2[0].trim { it <= ' ' }
        if (tokens2.size > 1)
            second2 = tokens2[1].trim { it <= ' ' }

        if (second2 != null) {
            val tokens3 = second2.split(" ".toRegex(), 2).toTypedArray()
            secondWeek = tokens3[0].trim { it <= ' ' }
            if (tokens3.size > 1)
                firstTimings = tokens3[1].trim { it <= ' ' }
        }

        if (second != null) {
            val tokens4 = second.split(" ".toRegex(), 2).toTypedArray()
            lastWeeks = tokens4[0].trim { it <= ' ' }
            if (tokens4.size > 1)
                lastTimings = tokens4[1].trim { it <= ' ' }
        }

        val tokens5 = firstWeeks.split("-".toRegex(), 2).toTypedArray()
        val string1 = tokens5[0]
        if (tokens5.size > 1) {
            if (tokens5[1].contains("-")) {
                val tokenString = tokens5[1].split(" ".toRegex(), 2).toTypedArray()
                string2 = tokenString[0]
                if (tokenString.size > 1) {
                    firstTimings = tokenString[1]
                }
            } else
                string2 = tokens5[1]
        }


        val index1 = weeksList.indexOf(string1)

        if (string2 != null) {
            val index2 = weeksList.indexOf(string2)
            firstWeeksList = weeksList.subList(index1, index2)
        } else
            secondWeeksList.add(weeksList[index1])


        if (string2 != null)
            firstWeeksList.add(string2)

        if (firstWeeksList.size > 0) {
            for (i in firstWeeksList.indices) {
                finalWeeksList.add(firstWeeksList[i] + " " + firstTimings)
            }
        }

        if (lastWeeks != null) {
            val tokens6 = lastWeeks.split("-".toRegex(), 2).toTypedArray()
            val string3 = tokens6[0]
            if (tokens6.size > 1)
                string4 = tokens6[1]

            val index3 = weeksList.indexOf(string3)

            if (string4 != null) {
                val index4 = weeksList.indexOf(string4)
                secondWeeksList = weeksList.subList(index3, index4)
            } else
                secondWeeksList.add(weeksList[index3])


            if (string4 != null)
                secondWeeksList.add(string4)

            if (secondWeeksList.size > 0) {
                for (i in secondWeeksList.indices) {
                    finalWeeksList.add(secondWeeksList[i] + " " + lastTimings)
                }
            }

        }

        if (secondWeek != null) {
            val tokens7 = secondWeek.split("-".toRegex(), 2).toTypedArray()
            val string5 = tokens7[0]
            if (tokens7.size > 1)
                string6 = tokens7[1]

            val index5 = weeksList.indexOf(string5)

            if (string6 != null) {
                val index6 = weeksList.indexOf(string6)
                thirdWeeksList = weeksList.subList(index5, index6)
            } else {
                thirdWeeksList.add(weeksList[index5])
            }


            if (string6 != null)
                thirdWeeksList.add(string6)

            if (thirdWeeksList.size > 0) {
                for (i in thirdWeeksList.indices) {
                    finalWeeksList.add(thirdWeeksList[i] + " " + firstTimings)
                }
            }
        }

        for (i in finalWeeksList.indices) {
            finalWeeksListString = finalWeeksListString + "\n" + finalWeeksList[i]
        }

        return finalWeeksListString
    }
}
