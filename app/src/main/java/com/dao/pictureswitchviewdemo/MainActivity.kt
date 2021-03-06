package com.dao.pictureswitchviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        select.setOnClickListener {
            pictureswitchview.setSelect(true    )
        }
        un_select.setOnClickListener {
            pictureswitchview.setSelect(false)
        }

        pictureswitchview.setOnClickListener{
            pictureswitchview.startAnimation()
        }

        switch_animation_effect.setOnClickListener {
            pictureswitchview.setAnimationStyle(if (pictureswitchview.getAnimationStyle() == 0) 1 else 0)
        }
    }
}
