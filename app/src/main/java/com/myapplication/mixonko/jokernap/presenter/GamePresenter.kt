package com.myapplication.mixonko.jokernap.presenter

import android.widget.Toast
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.model.MixGlasses
import com.myapplication.mixonko.jokernap.util.MyAppContext

class GamePresenter(val view: GameContract, val mix: MixGlasses = MixGlasses()) {
    private var lvl = 20
    private var rightChoice = 0
    private var timeInMillis = 500L

    fun onImageClick() {
        view.Toast()

    }

    fun onBackgroundClick() {
        startGame()
    }

    private fun startGame() {
        rightChoice = mix.randomJokerInThimble()
        animateTrimble()
        for (postDelayedMultiply in 1..lvl) {
            choiceAnim(mix.randomAnim(), postDelayedMultiply)
        }
        lvl++
        Toast.makeText(MyAppContext.getAppContext(), "$rightChoice", Toast.LENGTH_SHORT).show()
    }

    private fun animateTrimble() {
        when (rightChoice) {
            1 -> view.animateThimble1(timeInMillis)
            2 -> view.animateThimble2(timeInMillis)
            3 -> view.animateThimble3(timeInMillis)
        }
    }

    private fun choiceAnim(choiceAnim: Int, postDelayedMultiply: Int) {
        view.hideJokerAndShadow(timeInMillis)
        when (choiceAnim) {
            1 -> {
                if (rightChoice == 1) rightChoice = 2
                else if (rightChoice == 2) rightChoice = 1
                view.animate12(timeInMillis, postDelayedMultiply)
            }
            2 -> {
                if (rightChoice == 1) rightChoice = 3
                else if (rightChoice == 3) rightChoice = 1
                view.animate13(timeInMillis, postDelayedMultiply)
            }
            3 -> {
                if (rightChoice == 2) rightChoice = 1
                else if (rightChoice == 1) rightChoice = 2
                view.animate21(timeInMillis, postDelayedMultiply)
            }
            4 -> {
                if (rightChoice == 2) rightChoice = 3
                else if (rightChoice == 3) rightChoice = 2
                view.animate23(timeInMillis, postDelayedMultiply)
            }
            5 -> {
                if (rightChoice == 3) rightChoice = 1
                else if (rightChoice == 1) rightChoice = 3
                view.animate31(timeInMillis, postDelayedMultiply)
            }
            6 -> {
                if (rightChoice == 3) rightChoice = 2
                else if (rightChoice == 2) rightChoice = 3
                view.animate32(timeInMillis, postDelayedMultiply)
            }
        }
    }

    fun onCreate() {
        view.setDisable()
    }

}
