package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.network.data.AirQualityService

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)  // activity_home.xml 사용

        // 각 상태에 맞는 아이콘을 개별적으로 정의
        val goodIcon: ImageView = findViewById(R.id.good_icon)
        val moderateIcon: ImageView = findViewById(R.id.moderate_icon)
        val badIcon: ImageView = findViewById(R.id.bad_icon)
        val airQualityStatus: TextView = findViewById(R.id.air_quality_status)

        // 공공 API로부터 미세먼지 데이터를 가져옴
        val airQualityService = AirQualityService()
        airQualityService.getAirQualityData("Seoul") { result ->
            runOnUiThread {
                if (result != null) {
                    // JSON 데이터를 파싱하고 pm10Value를 얻음
                    val pm10Value = parsePm10Value(result)
                    // 미세먼지 농도에 따른 상태 업데이트
                    updateAirQuality(pm10Value, goodIcon, moderateIcon, badIcon, airQualityStatus)
                } else {
                    // 오류 처리 (예: 기본값 사용)
                    airQualityStatus.text = "데이터를 불러올 수 없습니다."
                }
            }
        }
    }

    private fun parsePm10Value(jsonResult: String): Int {
        // JSON 결과를 파싱하여 pm10Value를 추출하는 로직을 구현
        // 여기서는 간단히 예제용 하드코딩된 값을 반환
        return 30 // 실제로는 jsonResult를 파싱하여 해당 값을 반환해야 함
    }

    private fun updateAirQuality(
        pm10Value: Int,
        goodIcon: ImageView,
        moderateIcon: ImageView,
        badIcon: ImageView,
        statusView: TextView
    ) {
        // 모든 아이콘을 초기에는 숨김 처리
        goodIcon.visibility = View.GONE
        moderateIcon.visibility = View.GONE
        badIcon.visibility = View.GONE

        // 미세먼지 농도에 따라 해당 아이콘만 보이게 설정
        when {
            pm10Value <= 30 -> {
                goodIcon.visibility = View.VISIBLE
                statusView.text = "좋음"
                statusView.setTextColor(getColor(R.color.blue))
            }
            pm10Value in 31..80 -> {
                moderateIcon.visibility = View.VISIBLE
                statusView.text = "보통"
                statusView.setTextColor(getColor(R.color.yellow))
            }
            pm10Value > 80 -> {
                badIcon.visibility = View.VISIBLE
                statusView.text = "나쁨"
                statusView.setTextColor(getColor(R.color.red))
            }
        }
    }
}
