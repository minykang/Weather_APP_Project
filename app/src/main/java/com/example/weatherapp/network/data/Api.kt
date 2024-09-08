package com.example.weatherapp.network.data

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class AirQualityService {

    private val client = OkHttpClient()

    fun getAirQualityData(stationName: String, callback: (result: String?) -> Unit) {
        // 공공 데이터 API URL
        val apiUrl = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"
        val serviceKey = "tC2hI6KksUbKliwFS6dCD4ejHWDTzjbUFE5tWDRJpIJCgWEbsVi%2B%2BcYevwUHf%2FBS1WZDE45IImnVPZoSMAG27Q%3D%3D"  // 공공 데이터 포털에서 발급받은 API 키로 대체

        val url = "$apiUrl?serviceKey=$serviceKey&numOfRows=10&pageNo=1&stationName=$stationName&dataTerm=DAILY&ver=1.3&returnType=json"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.body?.string()?.let { responseBody ->
                    // JSON 파싱 예제
                    val jsonObject = JSONObject(responseBody)
                    val responseData = jsonObject.getJSONArray("response")
                    val airQualityData = responseData.getJSONObject(0)
                    callback(airQualityData.toString())
                }
            }
        })
    }
}
