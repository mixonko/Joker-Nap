package com.myapplication.mixonko.jokernap.view

import android.os.Bundle
import android.os.CountDownTimer
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
import java.text.SimpleDateFormat
import java.util.*
import android.util.DisplayMetrics
import android.view.Display
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class GameActivity : AppCompatActivity(), GameContract {

//    private fun dp(){
//        display  = windowManager.defaultDisplay
//        outMetrics: DisplayMetrics = DisplayMetrics()
//        display.getMetrics(outMetrics)
//        time.setText("${outMetrics.widthPixels/6}")
//        time.setText("${outMetrics.heightPixels}")
//    }

    private lateinit var countDownTimer: CountDownTimer

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
    private lateinit var background: ImageView
    private lateinit var thrimble1: ImageView
    private lateinit var thrimble2: ImageView
    private lateinit var thrimble3: ImageView
    private lateinit var youWin: ImageView
    private lateinit var youLose: ImageView
    private lateinit var shadow: ImageView
    private lateinit var time: TextView

    private lateinit var firstView: View
    private lateinit var secondView: View

    private lateinit var presenter: GamePresenter


    private lateinit var display: Display
    private lateinit var outMetrics: DisplayMetrics
    private var widthAnim: Float = 0F
    private var heightAnim: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenter(this)

        presenter.onCreate()

        setImageOnClickListener()

        display  = windowManager.defaultDisplay
        outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        widthAnim = (outMetrics.widthPixels/6).toFloat()
        heightAnim = (outMetrics.heightPixels/15).toFloat()
    }

    override fun startCountDownTimer(delayTime: Long) {
        Handler().postDelayed({
            countDownTimer = object : CountDownTimer(10000, 10) {

                override fun onTick(millisUntilFinished: Long) {
                    val mTimeFormat = SimpleDateFormat("ss:SS")
                    time.setText(mTimeFormat.format(Date(millisUntilFinished)))
                }

                override fun onFinish() {
                    time.setText(R.string.game_over)
                    presenter.timeIsOver()
                }
            }.start()
        }, delayTime)
    }

    override fun stopCountDownTimer() {
        countDownTimer.cancel()
    }

    override fun init() {
        jokerMiniImageView1 = findViewById(R.id.joker1)
        jokerMiniImageView2 = findViewById(R.id.joker2)
        jokerMiniImageView3 = findViewById(R.id.joker3)
        shadowMiniImageView1 = findViewById(R.id.shadow1)
        shadowMiniImageView2 = findViewById(R.id.shadow2)
        shadowMiniImageView3 = findViewById(R.id.shadow3)
        background = findViewById(R.id.background)
        time = findViewById(R.id.time)
        youWin = findViewById(R.id.you_win)
        youLose = findViewById(R.id.you_lose)
        shadow = findViewById(R.id.shadow)
        frameLayout = findViewById(R.id.frame)

    }

    override fun addView() {
        val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)
        firstView = layoutInflater.inflate(R.layout.first_game_field, null)
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
        youLose.setOnClickListener {
            presenter.onYouLoseClick()
        }
        background.setOnClickListener {
            frameLayout.removeView(firstView)
            frameLayout.addView(firstView)
            presenter.onBackgroundClick()
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

    override fun setThrimbleEnable() {
        thrimble1.isEnabled = true
        thrimble2.isEnabled = true
        thrimble3.isEnabled = true

    }

    override fun setThrimbleDisable() {
        thrimble1.isEnabled = false
        thrimble2.isEnabled = false
        thrimble3.isEnabled = false
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
                .translationXBy(widthAnim)
                .translationYBy(-heightAnim)
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(widthAnim)
                        .translationYBy(heightAnim)
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView2.animate()
                .translationXBy(-widthAnim)
                .translationYBy(heightAnim)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(-widthAnim)
                        .translationYBy(-heightAnim)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)
    }

    override fun animate13(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setFirstGameField()

            imageView1.animate()
                .translationXBy(widthAnim*2)
                .translationYBy(-heightAnim )
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(widthAnim*2)
                        .translationYBy(heightAnim )
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView3.animate()
                .translationXBy(-widthAnim*2)
                .translationYBy(heightAnim + 20)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-widthAnim*2)
                        .translationYBy(-heightAnim - 20)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate23(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setFirstGameField()

            imageView2.animate()
                .translationXBy(widthAnim)
                .translationYBy(-heightAnim)
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(widthAnim)
                        .translationYBy(heightAnim)
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView3.animate()
                .translationXBy(-widthAnim)
                .translationYBy(heightAnim)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-widthAnim)
                        .translationYBy(-heightAnim)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
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
                .translationXBy(-widthAnim)
                .translationYBy(-heightAnim)
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(-widthAnim)
                        .translationYBy(heightAnim)
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView1.animate()
                .translationXBy(widthAnim)
                .translationYBy(heightAnim)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(widthAnim)
                        .translationYBy(-heightAnim)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate31(timeInMillis: Long, postDelayedMultiply: Int)      {
        Handler().postDelayed({
            setSecondGameField()

            imageView3.animate()
                .translationXBy(-widthAnim*2)
                .translationYBy(-heightAnim)
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-widthAnim*2)
                        .translationYBy(heightAnim)
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView1.animate()
                .translationXBy(widthAnim*2)
                .translationYBy(heightAnim + 20)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView1.animate()
                        .translationXBy(widthAnim*2)
                        .translationYBy(-heightAnim - 20)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()
        }, timeInMillis * postDelayedMultiply)

    }

    override fun animate32(timeInMillis: Long, postDelayedMultiply: Int) {
        Handler().postDelayed({
            setSecondGameField()

            imageView3.animate()
                .translationXBy(-widthAnim)
                .translationYBy(-heightAnim)
                .scaleYBy(-0.4F)
                .scaleXBy(-0.4F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView3.animate()
                        .translationXBy(-widthAnim)
                        .translationYBy(heightAnim)
                        .scaleYBy(0.4F)
                        .scaleXBy(0.4F)
                        .setDuration(timeInMillis / 2)
                        .start()
                }.start()

            imageView2.animate()
                .translationXBy(widthAnim)
                .translationYBy(heightAnim)
                .scaleYBy(0.3F)
                .scaleXBy(0.3F)
                .setDuration(timeInMillis / 2)
                .withEndAction {
                    imageView2.animate()
                        .translationXBy(widthAnim)
                        .translationYBy(-heightAnim)
                        .scaleYBy(-0.3F)
                        .scaleXBy(-0.3F)
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

    override fun setBackgroundEnable() {
        background.isEnabled = true
    }

    override fun setBackgroundDisable() {
        background.isEnabled = false
    }

    override fun showYouLose() {
        Handler().postDelayed({
            youLose.visibility = View.VISIBLE
            shadow.visibility = View.VISIBLE
        }, 1000)
    }

    override fun hideYouLose() {
        youLose.visibility = View.INVISIBLE
        shadow.visibility = View.INVISIBLE

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

    override fun setText(text: String) {
        time.setText(text)
    }

}
