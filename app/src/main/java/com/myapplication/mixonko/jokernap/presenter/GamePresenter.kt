package com.myapplication.mixonko.jokernap.presenter

import android.widget.ImageView
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.model.MixGlasses

class GamePresenter(val view: GameContract, val mix: MixGlasses = MixGlasses()) {
    private var lvl = 15
    private var choice = 1
    private var timeInMillis = 1000L
    private lateinit var thrimbleRightChoice: ImageView

    fun onImageClick(thrimble: ImageView) {
        if (thrimble.getTag() == choice) {
            view.showJokerAndShadow()
            thrimbleRightChoice = thrimble
            view.showRightChoice(thrimble)
            view.showYouWin()
            view.setThrimbleDisable()
        } else {
            view.setThrimbleDisable()
            thrimbleRightChoice = thrimble
            view.showWrongChoice(thrimble)
            view.showYouLose()
            lvl = 1
        }

    }

    fun onBackgroundClick() {
        timeInMillis = (1000 - (30*lvl)).toLong()
        view.initImageView()
        view.hideMainThrimble()
        view.showJokerAndShadow()
        startGame()
        view.setBackgroundDisable()


    }

    private fun startGame() {
        choice = mix.randomJokerInThimble()

        animateTrimble(choice)

        view.hideJokerAndShadow(timeInMillis)

        animateTrimble(choice)

        for (postDelayedMultiply in 1..lvl) {
            choiceAnim(mix.randomAnim(), postDelayedMultiply)

        }
        view.setText("$timeInMillis")

        view.removeViewThrimble(timeInMillis * lvl + timeInMillis)
        view.showMainThrimble(timeInMillis * lvl + timeInMillis)

    }

    private fun animateTrimble(number: Int) {

        if (number == 1) view.animateThimble1(timeInMillis)
        if (number == 2) view.animateThimble2(timeInMillis)
        if (number == 3) view.animateThimble3(timeInMillis)
    }

    private fun choiceAnim(choiceAnim: Int, postDelayedMultiply: Int) {
        when (choiceAnim) {
            1 -> {
                view.animate12(timeInMillis, postDelayedMultiply)
                if (choice == 1) {
                    choice = 2
                    return
                }
                if (choice == 2) {
                    choice = 1
                    return
                }
            }
            2 -> {
                view.animate13(timeInMillis, postDelayedMultiply)
                if (choice == 1) {
                    choice = 3
                    return
                }
                if (choice == 3) {
                    choice = 1
                    return
                }
            }
            3 -> {
                view.animate21(timeInMillis, postDelayedMultiply)
                if (choice == 2) {
                    choice = 1
                    return
                }
                if (choice == 1) {
                    choice = 2
                    return
                }

            }
            4 -> {
                view.animate23(timeInMillis, postDelayedMultiply)
                if (choice == 2) {
                    choice = 3
                    return
                }
                if (choice == 3) {
                    choice = 2
                    return
                }

            }
            5 -> {
                view.animate31(timeInMillis, postDelayedMultiply)
                if (choice == 3) {
                    choice = 1
                    return
                }
                if (choice == 1) {
                    choice = 3
                    return
                }

            }
            6 -> {
                view.animate32(timeInMillis, postDelayedMultiply)
                if (choice == 3) {
                    choice = 2
                    return
                }
                if (choice == 2) {
                    choice = 3
                    return
                }

            }

        }

    }

    fun onCreate() {
        view.init()
        view.addView()
        view.initImageView()
    }

    fun onYouWinClick() {
        view.hideYouWin()
        lvl++
        view.setText("$lvl")
        update()

    }

    fun onYouLoseClick() {
        view.hideYouLose()
        lvl = 1
        view.setText("$lvl")
        update()

    }

    private fun update(){
        view.hideMainThrimble()
        view.hideChoice(thrimbleRightChoice)
        view.setThrimbleEnable()
        view.init()
        view.addView()
        view.initImageView()
        view.setBackgroundEnable()
    }

}
