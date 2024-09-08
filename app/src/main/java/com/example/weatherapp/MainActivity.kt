package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 백엔드에서 받은 위치 데이터를 반영 (여기서는 예시로 "서울특별시 양천구"라고 가정)
        val locationData = "서울특별시 양천구"

        // TextView에 위치 데이터를 설정
        val locationTextView = findViewById<TextView>(R.id.location_text_view)
        locationTextView.text = locationData

        // 백엔드에서 날씨 데이터를 받아오는 로직을 추가하세요 (예: 날씨 API 호출)
        // 받아온 데이터를 기반으로 시간별 및 일별 날씨 정보를 업데이트

        // 예시 데이터로 시간별 날씨를 업데이트
        updateHourlyForecast(listOf("10:00", "11:00", "12:00"), listOf("맑음", "흐림", "비"))

        // 예시 데이터로 일별 날씨를 업데이트
        updateDailyForecast(listOf("월요일", "화요일", "수요일"), listOf("맑음", "흐림", "비"))
    }

    // 시간별 예보를 업데이트하는 함수
    private fun updateHourlyForecast(times: List<String>, descriptions: List<String>) {
        val container = findViewById<LinearLayout>(R.id.hourly_forecast_container)
        container.removeAllViews()

        for (i in times.indices) {
            val timeTextView = TextView(this)
            timeTextView.text = "${times[i]}: ${descriptions[i]}"
            container.addView(timeTextView)
        }
    }

    // 일별 예보를 업데이트하는 함수
    private fun updateDailyForecast(days: List<String>, descriptions: List<String>) {
        val container = findViewById<LinearLayout>(R.id.daily_forecast_container)
        container.removeAllViews()

        for (i in days.indices) {
            val dayTextView = TextView(this)
            dayTextView.text = "${days[i]}: ${descriptions[i]}"
            container.addView(dayTextView)
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
