package com.myapplication.mixonko.jokernap.presenter

import com.myapplication.mixonko.jokernap.contract.GameContract

class GamePresenter(val view: GameContract)   {
    private var lvl = 1
    private var randomAnim = 1
    private var randomThimble = 1

    fun onImageClick() {
        view.animate13()

    }

    fun onBackgroundClick() {

    }

}
