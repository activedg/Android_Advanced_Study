package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        private const val TAG = "로그"
    }
    lateinit var binding: ActivityMainBinding
    // by viewModels()로 초기화 하는 방법
    private val myNumberViewModel: MyNumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // myNumberViewModel = ViewModelProvider(this)[MyNumberViewModel::class.java]

        // ViewModel의 LiveData에 접근
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - 라이브 데이터 값 변경 : $it")
            binding.numberTv.text = it.toString()
        })

        // 리스너 연결
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val userInput = binding.userInputEt.text.toString().toInt()

        // 라이브 데이터 값 변경하는 메서드 실행
        when(view){
            binding.plusBtn -> myNumberViewModel.updateValue(ActionType.PLUS, userInput)
            binding.minusBtn -> myNumberViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}