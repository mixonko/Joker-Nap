package com.myapplication.mixonko.jokernap.presenter

import android.widget.ImageView
import android.widget.Toast
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.model.MixGlasses
import com.myapplication.mixonko.jokernap.util.MyAppContext

class GamePresenter(val view: GameContract, val mix: MixGlasses = MixGlasses()) {
    private var lvl = 10
    private var rightChoice = 1
    private var choice = 1
    private var timeInMillis = 500L
    private lateinit var thrimbleRightChoice: ImageView

    fun onImageClick(thrimble: ImageView) {
        if(thrimble.getTag() == choice){
            view.showJokerAndShadow()
            thrimbleRightChoice = thrimble
            view.showRightChoice(thrimble)
            view.showYouWin()
            lvl++
        }else{
            view.showWrongChoice(thrimble)
            view.showYouLose()
            lvl = 2
        }

    }

    fun onBackgroundClick() {
        view.initImageView()
        view.hideMainThrimble()
        view.showJokerAndShadow()
        startGame()

    }

    private fun startGame() {
        rightChoice = mix.randomJokerInThimble()
        choice = rightChoice
        view.hideJokerAndShadow(timeInMillis)

        for (postDelayedMultiply in 1..lvl ) {
            choiceAnim(mix.randomAnim(), postDelayedMultiply)

        }
        animateTrimble(choice)
        view.setText("$choice")
        choice = rightChoice

        Toast.makeText(MyAppContext.getAppContext(), "$rightChoice + $choice ", Toast.LENGTH_SHORT).show()

        view.removeViewThrimble(timeInMillis * lvl + timeInMillis)
        view.showMainThrimble(timeInMillis * lvl + timeInMillis)



    }

    private fun animateTrimble(number: Int) {
//        when (number) {
//            1 -> view.animateThimble1(timeInMillis)
//            2 -> view.animateThimble2(timeInMillis)
//            3 -> view.animateThimble3(timeInMillis)
//        }
        if (number == 1)view.animateThimble1(timeInMillis)
        if (number == 2)view.animateThimble2(timeInMillis)
        if (number == 3)view.animateThimble3(timeInMillis)
    }

    private fun choiceAnim(choiceAnim: Int, postDelayedMultiply: Int) {
        when (choiceAnim) {
            1 -> {
                view.animate12(timeInMillis, postDelayedMultiply)
                if (rightChoice == 1){
                    rightChoice = 2
                    return
                }
                if (rightChoice == 2) {
                    rightChoice = 1
                    return
                }
            }
            2 -> {
                view.animate13(timeInMillis, postDelayedMultiply)
                if (rightChoice == 1){
                    rightChoice = 3
                    return
                }
                if (rightChoice == 3) {
                    rightChoice = 1
                    return
                }
            }
            3 -> {
                view.animate21(timeInMillis, postDelayedMultiply)
                if (rightChoice == 2) {
                    rightChoice = 1
                    return
                }
                if (rightChoice == 1){
                    rightChoice = 2
                    return
                }

            }
            4 -> {
                view.animate23(timeInMillis, postDelayedMultiply)
                if (rightChoice == 2){
                    rightChoice = 3
                    return
                }
                if (rightChoice == 3){
                    rightChoice = 2
                    return
                }

            }
            5 -> {
                view.animate31(timeInMillis, postDelayedMultiply)
                if (rightChoice == 3) {
                    rightChoice = 1
                    return
                }
                if (rightChoice == 1) {
                    rightChoice = 3
                    return
                }

            }
            6 -> {
                view.animate32(timeInMillis, postDelayedMultiply)
                if (rightChoice == 3) {
                    rightChoice = 2
                    return
                }
                if (rightChoice == 2) {
                    rightChoice = 3
                    return
                }

            }

        }
        view.setText("$rightChoice")

    }

    fun onCreate() {
        view.init()
        view.initImageView()
    }

    fun onYouWinClick() {
        view.hideYouWin()
        view.hideChoice(thrimbleRightChoice)
    }

}
