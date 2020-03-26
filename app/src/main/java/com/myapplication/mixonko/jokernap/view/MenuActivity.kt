package com.myapplication.mixonko.jokernap.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.myapplication.mixonko.jokernap.R
import com.myapplication.mixonko.jokernap.contract.MenuContract
import com.myapplication.mixonko.jokernap.presenter.MenuPresenter

class MenuActivity : AppCompatActivity(), MenuContract {

    private lateinit var presenter: MenuPresenter
    private lateinit var play: ImageView
    private lateinit var settings: ImageView
    private lateinit var webView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        presenter = MenuPresenter(this)

        init()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        play.setOnClickListener {
            presenter.onPlayClick()
        }
//        settings.setOnClickListener {
//            presenter.onSettingsClick()
//        }
        webView.setOnClickListener {
            presenter.onWebViewClick()
        }
    }

    fun init(){
        play = findViewById(R.id.playButton)
//        settings = findViewById(R.id.settingsButton)
        webView = findViewById(R.id.web_view_button)
    }

    override fun startGameActivity() {
        startActivity(Intent(this, GameActivity::class.java))
    }

    override fun startSettings() {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
    }

    override fun startWebViewActivity() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }

}
