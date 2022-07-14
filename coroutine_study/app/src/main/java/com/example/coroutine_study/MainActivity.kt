package com.example.coroutine_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.coroutine_study.databinding.ActivityMainBinding
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lottoBtn.setOnClickListener{
            val lottoNumbers = createLottoNumbers()
            Log.d("main", lottoNumbers.toString())
            updateLottoBallImage(lottoNumbers)

            CoroutineScope(Dispatchers.IO).launch {
                // 네트워크에서 받아온 후 값 반환, 반환 값 존재 -> async
                val winningNumbers = async { getLottoNumbers() }
                // winnningNumbers -> deffered 형태 -> 값 받아오는거 끝날 때 까지 await
                val rank = whatIsRank(lottoNumbers, winningNumbers.await())
                val text = "${winningNumbers.await()} : $rank"

                withContext(Dispatchers.Main){
                    binding.lottoWinningTv.text = text
                }
            }
        }
    }

    private fun createLottoNumbers(): ArrayList<Int>{
        val result = arrayListOf<Int>()

        val source = IntArray(45){it + 1}
        val seed = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).format(Date()).format(Date()).hashCode().toLong()
        val random = Random(seed)
        source.shuffle(random)
        source.slice(0..5).forEach{ num ->
            result.add(num)
        }
        result.sort()

        var evenNumberCnt = 0
        var oddNumberCnt = 0
        for (num in result){
            if (num % 2 == 0)
                evenNumberCnt += 1
            else
                oddNumberCnt += 1
        }
        result.add(result.sum())
        result.add(oddNumberCnt)
        result.add(evenNumberCnt)
        return result
    }

    private fun getDrawableId(number: Int): Int{
        val number = String.format("%02d", number)
        val string = "ball_$number"
        val id = resources.getIdentifier(string, "drawable", packageName)
        return id
    }

    private fun updateLottoBallImage(result: ArrayList<Int>){
        with(binding){
            lottoIv01.setImageResource(getDrawableId(result[0]))
            lottoIv02.setImageResource(getDrawableId(result[1]))
            lottoIv03.setImageResource(getDrawableId(result[2]))
            lottoIv04.setImageResource(getDrawableId(result[3]))
            lottoIv05.setImageResource(getDrawableId(result[4]))
            lottoIv06.setImageResource(getDrawableId(result[5]))
            lottoResTv.text = "번호합 : ${result[6]} 홀: 짝=${result[7]}: ${result[8]}"
        }
    }

    private suspend fun getLottoNumbers(): ArrayList<Int>{
        val round = "1023"
        val url = "https://dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=${round}"
        val lottoNumbers = ArrayList<Int>()

        try{
            val response = URL(url).readText()
            val jsonObject = JsonParser.parseString(response).asJsonObject
            val returnValue = jsonObject.get("returnValue").asString

            if (returnValue == "success"){
                for (i in 1..6){
                    val lottoNumber = jsonObject.get("drwtNo$i").asInt
                    lottoNumbers.add(lottoNumber)
                }
                val bonusNumber = jsonObject.get("bnusNo").asInt
                lottoNumbers.add(bonusNumber)
                lottoNumbers.add(round.toInt())
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
        return lottoNumbers
    }

    private fun whatIsRank(lottoNumbers: ArrayList<Int>, winningNumbers: ArrayList<Int>) : String{
        var matchCount = 0
        for (i in 0..5){
            if (lottoNumbers.contains(winningNumbers[i])){
                matchCount += 1
            }
        }
        return if (matchCount == 6){
            "1등"
        } else if (matchCount == 5){
            if(lottoNumbers.contains((winningNumbers[6]))){
                "2등"
            } else{
                "3등"
            }
        } else if (matchCount == 4){
            "4등"
        } else if (matchCount == 3){
            "5등"
        } else {
            "낙첨"
        }
    }
}