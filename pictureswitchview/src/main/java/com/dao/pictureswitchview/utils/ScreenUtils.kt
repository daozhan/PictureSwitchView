package com.dao.practicedraw.utils

import android.content.res.Resources
import android.view.View
import android.util.DisplayMetrics



/**
 *
 * @author daoz
 * @date :2020/4/11 11:14
 */

fun View.dip2Px(dpValue: Float): Float {
//    val scale = this.context.resources.displayMetrics.density
//    return dpValue * scale
    return Math.round(resources.displayMetrics.density * dpValue).toFloat();
}

object ScreenUtils{
    fun dpToPixel(dp: Float): Float {
        val metrics = Resources.getSystem().getDisplayMetrics()
        return dp * metrics.density
    }
}