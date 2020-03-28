package com.myapplication.mixonko.jokernap.contract

import android.widget.ImageView

interface GameContract {
    fun animateThimble1(timeInMillis: Long)
    fun animateThimble2(timeInMillis: Long)
    fun animateThimble3(timeInMillis: Long)
    fun showYouWin()
    fun hideYouWin()
    fun showYouLose()
    fun hideYouLose()
    fun setThrimbleEnable()
    fun setThrimbleDisable()
    fun setBackgroundDisable()
    fun setBackgroundEnable()
    fun animate12(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate13(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate21(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate23(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate31(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate32(timeInMillis: Long, postDelayedMultiply : Int)
    fun hideJokerAndShadow(timeInMillis: Long)
    fun showJokerAndShadow()
    fun Toast( )
    fun removeViewThrimble(timeInMillis: Long)
    fun showMainThrimble(timeInMillis: Long)
    fun hideMainThrimble()
    fun init()
    fun initImageView()
    fun showRightChoice(thrimble: ImageView)
    fun showWrongChoice(thrimble: ImageView)
    fun hideChoice(thrimbleRightChoice: ImageView)
    fun setText(text: String)
    fun addView()
    fun stopCountDownTimer()
    fun startCountDownTimer(delayTime: Long)

}
