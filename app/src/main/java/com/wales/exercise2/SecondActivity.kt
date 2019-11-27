package com.wales.exercise2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.math.floor
import kotlin.math.round

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var degree = intent.getIntExtra("degree", 0)
        txt_page2.text = "第二頁 $degree"
        var b = floor(0.5 * 1.4)
        println(b)
        btn_close.setOnClickListener {
            finish()
        }
    }


    fun evenNumbers(array: List<Int>, number: Int): List<Int> {
        var result = mutableListOf<Int>()
        while (result.size < number) {
            array.forEach {
                println(it)
                if (it % 2 == 0) {
                    result.add(it)
                }
            }
        }
        return result
    }
}
