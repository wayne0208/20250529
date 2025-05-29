package com.example.a20250529
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 將變數與XML元件綁定
        val imgFrame = findViewById<ImageView>(R.id.imgFrame)
        val imgTween = findViewById<ImageView>(R.id.imgTween)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnAlpha = findViewById<Button>(R.id.btnAlpha)
        val btnScale = findViewById<Button>(R.id.btnScale)
        val btnTranslate = findViewById<Button>(R.id.btnTranslate)
        val btnRotate = findViewById<Button>(R.id.btnRotate)

        // 將圖片背景轉為AnimationDrawable
        val frameAnim = imgFrame.background as AnimationDrawable

        btnStart.setOnClickListener {
            frameAnim.start()
        }

        btnStop.setOnClickListener {
            frameAnim.stop()
        }

        btnAlpha.setOnClickListener {
            val anim = AlphaAnimation(
                1.0f, // 起始透明度
                0.2f // 結束透明度
            )
            anim.duration = 1000 // 動畫持續一秒
            imgTween.startAnimation(anim) // 執行動畫
        }

        btnScale.setOnClickListener {
            val anim = ScaleAnimation(
                1.0f, // X起始比例
                1.5f, // X結束比例
                1.0f, // Y起始比例
                1.5f // Y結束比例
            )
            anim.duration = 1000 // 動畫持續一秒
            imgTween.startAnimation(anim) // 執行動畫
        }
        btnTranslate.setOnClickListener {
            // Animate the image to move right by 200 pixels
            // 圖片往右移動 (TranslationX)
            imgTween.animate()
                .translationXBy(200f) // Move an additional 200 pixels from current position
                .duration = 1000 // Animation duration in milliseconds (1 second)
            // If you want to move to an absolute position, use translationX(200f)
            // If you want it to move back to origin after moving, you'd chain another animation
            // or reset its position. For a simple "move right", translationXBy is good.
        }

        // Set click listener for the "旋轉" (Rotate) button
        btnRotate.setOnClickListener {
            // Animate the image to rotate 360 degrees clockwise
            // 圖片順時鐘轉一圈 (Rotation)
            imgTween.animate()
                .rotationBy(360f) // Rotate by 360 degrees from current rotation
                .duration = 1000 // Animation duration in milliseconds (1 second)
            // If you want to ensure it always starts from 0 degrees for rotation,
            // you might set imgTween.rotation = 0f before starting the animation,
            // or use ObjectAnimator if you need more precise control over start/end values.
            // Example using ObjectAnimator for rotation (more explicit):
            /*
            ObjectAnimator.ofFloat(binding.imgTween, "rotation", 0f, 360f).apply {
                duration = 1000
                start()
            }
            */
        }
    }
}
