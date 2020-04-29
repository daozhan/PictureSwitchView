package com.dao.pictureswitchview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlin.math.max

/**
 *
 * @author daoz
 * @date :2020/4/28 15:55
 */
class PictureSwitchView : FrameLayout {
    private val pictureSwitchImage = PictureSwitchImage(context)
    private val pictureSwitchBgImage = PictureSwitchBgImage(context)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val custom = context?.obtainStyledAttributes(attrs, R.styleable.PictureSwitchView, defStyleAttr, 0)
        val selectSrc = custom?.getResourceId(R.styleable.PictureSwitchView_selectSrc, R.drawable.tab_home_s)
        val unSelectSrc = custom?.getResourceId(R.styleable.PictureSwitchView_unSelectSrc, R.drawable.tab_home_n)
        // 动画时间
        val animationTime = custom?.getInteger(R.styleable.PictureSwitchView_loadingTextAnimationTime, 200)
        if (selectSrc != null) {
            pictureSwitchImage.setSelectBitmapSrc(selectSrc)
        }
        if (unSelectSrc != null) {
            pictureSwitchBgImage.setUnSelectBitmapSrc(unSelectSrc)
        }
        if (animationTime != null) {
            pictureSwitchImage.setLoadingAnimationTime(animationTime)
            pictureSwitchBgImage.setLoadingAnimationTime(animationTime)
        }
    }

    init {
        addView(pictureSwitchBgImage)
        addView(pictureSwitchImage)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            max(pictureSwitchBgImage.measuredWidth, pictureSwitchImage.measuredWidth),
            max(pictureSwitchBgImage.measuredHeight, pictureSwitchImage.measuredHeight)
        )
    }

    fun startAnimation() {
        pictureSwitchImage.startAnimation()
        pictureSwitchBgImage.startAnimation()
    }

    fun setSelect(isSelect : Boolean){
        if (isSelect == pictureSwitchImage.getIsSelect()){
            pictureSwitchImage.setIsSelect(isSelect)
            pictureSwitchBgImage.setIsSelect(isSelect)
        }
    }

    fun getSelect() : Boolean{
        return pictureSwitchImage.getIsSelect()
    }

}