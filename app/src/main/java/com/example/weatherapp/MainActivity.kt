package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 예시 데이터: 미세먼지 농도
        val pm10Value = 30 // 실제 API나 센서 데이터를 통해 얻은 값

        // 아이콘 및 상태를 업데이트할 뷰
        val airQualityIcon: ImageView = findViewById(R.id.air_quality_icon)
        val airQualityStatus: TextView = findViewById(R.id.air_quality_status)

        // 미세먼지 농도에 따른 상태 업데이트
        updateAirQuality(pm10Value, airQualityIcon, airQualityStatus)
    }

    private fun updateAirQuality(pm10Value: Int, iconView: ImageView, statusView: TextView) {
        when {
            pm10Value <= 30 -> {
                // 좋음 상태
                iconView.setImageResource(R.drawable.good_icon) // `good_icon`은 좋음 상태의 아이콘
                statusView.text = "좋음"
                statusView.setTextColor(getColor(R.color.blue)) // 파란색 텍스트
            }
            pm10Value in 31..80 -> {
                // 보통 상태
                iconView.setImageResource(R.drawable.moderate_icon) // `moderate_icon`은 보통 상태의 아이콘
                statusView.text = "보통"
                statusView.setTextColor(getColor(R.color.yellow)) // 노란색 텍스트
            }
            pm10Value > 80 -> {
                // 나쁨 상태
                iconView.setImageResource(R.drawable.bad_icon) // `bad_icon`은 나쁨 상태의 아이콘
                statusView.text = "나쁨"
                statusView.setTextColor(getColor(R.color.red)) // 빨간색 텍스트
            }
        }
    }

    // 옷 추천 버튼 클릭 시 실행될 메서드
    fun openClothingRecommendation(view: View) {
        val intent = Intent(this, ClothingRecommendationActivity::class.java)
        startActivity(intent)
    }

    // 추천 음식 버튼 클릭 시 실행될 메서드
    fun openFoodRecommendation(view: View) {
        val intent = Intent(this, FoodRecommendationActivity::class.java)
        startActivity(intent)
    }

    // 플레이리스트 버튼 클릭 시 실행될 메서드
    fun openPlaylist(view: View) {
        val intent = Intent(this, PlaylistActivity::class.java)
        startActivity(intent)
    }

    // 지역별 날씨 버튼 클릭 시 실행될 메서드
    fun openRegionalWeather(view: View) {
        val intent = Intent(this, RegionalWeatherActivity::class.java)
        startActivity(intent)
    }
}
