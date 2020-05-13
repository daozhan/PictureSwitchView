package com.dao.pictureswitchview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import com.dao.practicedraw.utils.dip2Px
import android.animation.AnimatorSet
import android.util.Log
import kotlin.math.sqrt


/**
 * 选中图片
 * @author daoz
 * @date :2020/4/28 15:57
 */
class PictureSwitchImage : View {
    // 选择图片
    private var mSelectBitmap: Bitmap? = null
    // 图片宽度
    private var imageWidth = mSelectBitmap?.width?.toFloat()
    // 图片高度
    private var imageHeight = mSelectBitmap?.height?.toFloat()
    private val paint = Paint()
    private val path = Path()
    // 是否选择 默认为未选中
    private var isSelect = true
    // 动画时间
    private var animationTime = 200
    // 移动的距离
    private var movingDistance = dip2Px(0f)
    // 圆的半径
    private var radius: Float = 0.0f
    // 动画模式
    private var animationStyle = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (imageWidth != null && imageHeight != null) {
            path.reset()
            if (animationStyle == 0) {
                path.rLineTo(imageWidth!!, dip2Px(0f))
                path.lineTo(imageWidth!!, movingDistance)
                path.rLineTo(-imageWidth!!, dip2Px(0f))
                path.close()
            } else {
                path.addCircle(imageWidth!! / 2, imageHeight!! / 2, movingDistance, Path.Direction.CW)
            }
            canvas?.clipPath(path)
            canvas?.drawBitmap(mSelectBitmap, dip2Px(0f), dip2Px(0f), paint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(imageWidth?.toInt()!!, imageHeight?.toInt()!!)
    }

    /**
     * 开始动画
     */
    fun startAnimation() {
        isSelect = !isSelect
        if (imageWidth != null && imageHeight != null) {
            // 覆盖动画
            val animator = ObjectAnimator.ofFloat(
                if (isSelect) if (animationStyle == 0) imageHeight!! else radius else dip2Px(0f),
                if (isSelect) dip2Px(0f) else if (animationStyle == 0) imageHeight!! else radius
            )
//            val animator = ObjectAnimator.ofFloat(
//                if(isSelect) radius else dip2Px(0f),
//                if(isSelect) dip2Px(0f) else radius
//            )
            animator.duration = animationTime.toLong()
            animator.addUpdateListener {
                movingDistance = it.animatedValue as Float
                invalidate()
            }
            animator.start()
        }
    }

    /**
     * 设置选中图片
     */
    fun setSelectBitmapSrc(@IdRes selectBitmapSrc: Int) {
        mSelectBitmap = BitmapFactory.decodeResource(resources, selectBitmapSrc)
        imageWidth = mSelectBitmap?.width?.toFloat()
        imageHeight = mSelectBitmap?.height?.toFloat()
        radius = sqrt((imageWidth!! / 2) * (imageWidth!! / 2) + (imageHeight!! / 2) * (imageHeight!! / 2))
        invalidate()
    }

    /**
     * 设置动画时间
     */
    fun setLoadingAnimationTime(animationTime: Int) {
        this.animationTime = animationTime
        invalidate()
    }

    /**
     * 设置选中
     */
    fun setIsSelect(isSelect: Boolean) {
        this.isSelect = isSelect
        startAnimation()
        Log.e("dao", "setIsSelect")
    }

    fun getIsSelect(): Boolean {
        return isSelect
    }

    fun setAnimationStyle(animationStyle: Int) {
        this.animationStyle = animationStyle
    }

    fun getAnimationStyle() : Int{
        return animationStyle
    }
}