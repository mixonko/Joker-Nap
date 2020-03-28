package com.myapplication.mixonko.jokernap.contract

interface GameContract {
    fun animateThimble1(timeInMillis: Long)
    fun animateThimble2(timeInMillis: Long)
    fun animateThimble3(timeInMillis: Long)
    fun showYouWin()
    fun setEnable()
    fun setDisable()
    fun animate12(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate13(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate21(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate23(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate31(timeInMillis: Long, postDelayedMultiply : Int)
    fun animate32(timeInMillis: Long, postDelayedMultiply : Int)
    fun hideJokerAndShadow(timeInMillis: Long)
    fun showJokerAndShadow()
    fun Toast( )
}
