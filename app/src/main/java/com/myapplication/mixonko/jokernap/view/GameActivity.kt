package com.myapplication.mixonko.jokernap.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.mixonko.jokernap.R
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.presenter.GamePresenter
import java.lang.Exception


class GameActivity : AppCompatActivity(), GameContract {
    override fun setText(text: String) {
        time.setText(text)
    }

    override fun showYouLose() {
        Toast.makeText(this, "lose", Toast.LENGTH_SHORT).show()
    }

    override fun hideYouLose() {
        Toast.makeText(this, "hide lose", Toast.LENGTH_SHORT).show()

    }

    override fun showWrongChoice(thrimble: ImageView) {
        thrimble.animate()
            .translationYBy(-200F)
            .start()
    }

    override fun hideChoice(thrimbleRightChoice: ImageView) {
        thrimbleRightChoice.animate()
            .translationYBy(200F)
            .start()
    }

    override fun showRightChoice(thrimble: ImageView) {
        thrimble.animate()
            .translationYBy(-200F)
            .start()
    }

    private lateinit var jokerMiniImageView1: ImageView
    private lateinit var jokerMiniImageView2: ImageView
    private lateinit var jokerMiniImageView3: ImageView
    private lateinit var shadowMiniImageView1: ImageView
    private lateinit var shadowMiniImageView2: ImageView
    private lateinit var shadowMiniImageView3: ImageView

    private lateinit var frameLayout: FrameLayout
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var thrimble1: ImageView
    private lateinit var thrimble2: ImageView
    private lateinit var thrimble3: ImageView
    private lateinit var youWin: ImageView
    private lateinit var shadow: ImageView
    private lateinit var time: TextView

    private lateinit var firstView: View
    private lateinit var secondView: View

    private lateinit var presenter: GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenter(this)

        presenter.onCreate()

        setImageOnClickListener()

    }

    override fun init() {
        jokerMiniImageView1 = findViewById(R.id.joker1)
        jokerMiniImageView2 = findViewById(R.id.joker2)
        jokerMiniImageView3 = findViewById(R.id.joker3)
        shadowMiniImageView1 = findViewById(R.id.shadow1)
        shadowMiniImageView2 = findViewById(R.id.shadow2)
        shadowMiniImageView3 = findViewById(R.id.shadow3)
        time = findViewById(R.id.time)
        youWin = findViewById(R.id.you_win)
        shadow = findViewById(R.id.shadow)
        frameLayout = findViewById(R.id.frame)

        val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)
        firstView = layoutInflater.inflate(R.layout.first_game_field, null)
        frameLayout.setOnClickListener {
            frameLayout.removeView(firstView)
            frameLayout.addView(firstView)
            presenter.onBackgroundClick()

        }
        frameLayout.addView(firstView)
    }

    override fun initImageView() {
        imageView1 = findViewById(R.id.image_view_1)
        imageView2 = findViewById(R.id.image_view_2)
        imageView3 = findViewById(R.id.image_view_3)
        thrimble1 = findViewById(R.id.thrimble1)
        thrimble2 = findViewById(R.id.thrimble2)
        thrimble3 = findViewById(R.id.thrimble3)
        thrimble1.setTag(1)
        thrimble2.setTag(2)
        thrimble3.setTag(3)
    }

    private fun setImageOnClickListener() {
            thrimble1.setOnClickListener {
            presenter.onImageClick(thrimble1)

        }
        thrimble2.setOnClickListener {
            presenter.onImageClick(thrimble2)


        }
        thrimble3.setOnClickListener {
            presenter.onImageClick(thrimble3)

        }

        youWin.setOnClickListener {
            presenter.onYouWinClick()
          }

    }

    override fun showYouWin() {
        Handler().postDelayed({
            youWin.visibility = View.VISIBLE
            shadow.visibility = View.VISIBLE
        }, 1000)
    }

    override fun hideYouWin() {
        youWin.visibility = View.INVISIBLE
        shadow.visibility = View.INVISIBLE
    }

    override fun setEnable() {
        imageView1.isEnabled = true
        imageView2.isEnabled = true
        imageView3.isEnabled = true

    }

    override fun setDisable() {
//        imageView1.isEnabled = false
//        imageView2.isEnabled = false
//        imageView3.isEnabled = false
    }

    private fun setFirstGameField() {

        try {
            frameLayout.removeView(firstView)
            frameLayout.removeView(secondView)
        } catch (e: Exception) {
        }

        firstView = layoutInflater.inflate(R.layout.first_game_field, null)

        frameLayout.addView(firstView)

        initImageView()

    }

    private fun setSecondGameField() {
        try {
            frameLayout.removeView(firstView)
            frameLayout.removeView(secondView)
        } catch (e: Exception) {
        }

        secondView = layoutInflater.inflate(R.layout.second_game_field, null)

        frameLayout.addView(secondView)

        initImageView()

    }

    override fun animate12(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setFirstGameField()
            imageView1.animate()
                .translationXBy(180F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(180F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView2.animate()
                .translationXBy(-180F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(-180F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)
    }

    override fun animate13(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setFirstGameField()

            imageView1.animate()
                .translationXBy(360F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(360F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView3.animate()
                .translationXBy(-360F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-360F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate23(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setFirstGameField()

            imageView2.animate()
                .translationXBy(180F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(180F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView3.animate()
                .translationXBy(-180F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-180F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    override fun animate21(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setSecondGameField()

            imageView2.animate()
                .translationXBy(-180F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(-180F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView1.animate()
                .translationXBy(180F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(180F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate31(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setSecondGameField()

            imageView3.animate()
                .translationXBy(-360F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-360F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView1.animate()
                .translationXBy(360F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(360F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate32(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setSecondGameField()

            imageView3.animate()
                .translationXBy(-180F)
                .translationYBy(-100F)
                .scaleYBy(-0.3F)
                .scaleXBy(-0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-180F)
                        .translationYBy(100F)
                        .scaleYBy(0.3F)
                        .scaleXBy(0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView2.animate()
                .translationXBy(180F)
                .translationYBy(100F)
                .scaleYBy(0.2F)
                .scaleXBy(0.2F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(180F)
                        .translationYBy(-100F)
                        .scaleYBy(-0.2F)
                        .scaleXBy(-0.2F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun hideJokerAndShadow(timeInMillis: Long) {
        Handler().postDelayed({
            jokerMiniImageView1.visibility = View.INVISIBLE
            jokerMiniImageView2.visibility = View.INVISIBLE
            jokerMiniImageView3.visibility = View.INVISIBLE
            shadowMiniImageView1.visibility = View.INVISIBLE
            shadowMiniImageView2.visibility = View.INVISIBLE
            shadowMiniImageView3.visibility = View.INVISIBLE
        }, timeInMillis)
    }

    override fun showJokerAndShadow() {
        jokerMiniImageView1.visibility = View.VISIBLE
        jokerMiniImageView2.visibility = View.VISIBLE
        jokerMiniImageView3.visibility = View.VISIBLE
        shadowMiniImageView1.visibility = View.VISIBLE
        shadowMiniImageView2.visibility = View.VISIBLE
        shadowMiniImageView3.visibility = View.VISIBLE
    }

    override fun animateThimble1(timeInMillis: Long) {
        imageView1.animate()
            .translationYBy(-200F)
            .setDuration(timeInMillis / 2)
            .withEndAction {
                imageView1.animate()
                    .translationYBy(200F)
                    .setDuration(timeInMillis / 2)
                    .start()
            }.start()

    }

    override fun animateThimble2(timeInMillis: Long) {
        imageView2.animate()
            .translationYBy(-200F)
            .setDuration(timeInMillis / 2)
            .withEndAction {
                imageView2.animate()
                    .translationYBy(200F)
                    .setDuration(timeInMillis / 2)
                    .start()
            }.start()
    }

    override fun animateThimble3(timeInMillis: Long) {
        imageView3.animate()
            .translationYBy(-200F)
            .setDuration(timeInMillis / 2)
            .withEndAction {
                imageView3.animate()
                    .translationYBy(200F)
                    .setDuration(timeInMillis / 2)
                    .start()
            }.start()
    }

    override fun removeViewThrimble(timeInMillis: Long) {

        Handler().postDelayed({
            try {
                frameLayout.removeView(firstView)
                frameLayout.removeView(secondView)

            } catch (e: Exception) {
            }
        }, timeInMillis)

    }

    override fun showMainThrimble(timeInMillis: Long) {
        Handler().postDelayed({
            thrimble1.visibility = View.VISIBLE
            thrimble2.visibility = View.VISIBLE
            thrimble3.visibility = View.VISIBLE
        }, timeInMillis)
    }

    override fun hideMainThrimble() {
        thrimble1.visibility = View.INVISIBLE
        thrimble2.visibility = View.INVISIBLE
        thrimble3.visibility = View.INVISIBLE
    }

    override fun Toast() {
        Toast.makeText(this, "asdsadsad", Toast.LENGTH_SHORT).show()

    }
}
