package com.example.testapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import com.example.testapp.databinding.ActivityGameBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var handler: Handler
    var res = 0
    val strMole = "X"
    var strBtn = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())

        var timercount = 4
        val tvTimer = binding.tvCurTimer

        var tvScore = binding.tvCurScore
        tvScore.text = res.toString()
        val countDownTimer = object : CountDownTimer(30000 , 1000) {
            override fun onTick(millis: Long) {
                tvTimer.text = timercount.toString()
                timercount--


            }
            override fun onFinish() {

                val intent = Intent(binding.root.context, EndActivity::class.java)
                intent.putExtra("SCORE", res)
                startActivity(intent)
            }
        }


        val preTimer = object : CountDownTimer(4000 , 1000) {
            override fun onTick(millis: Long) {
                timercount--
                if (timercount!=0) {
                    binding.tvGo.text = timercount.toString()
                }
                else{
                    binding.tvGo.text = "GO!"
                }


            }
            override fun onFinish() {
                strBtn = strMole
                timercount=30
                countDownTimer.start()
                binding.tvGo.text =""



            }
        }

        preTimer.start()




        val table = binding.tableLayout
        for (i in 0..2){
            val tableRow = TableRow(this)
            val buttonsrow = mutableListOf<Button>()
            for (j in 0..2){
                val button = Button(this)
                button.setOnClickListener(Listener(i, j))
                //buttonsrow.add(button)
                button.width = 300
                button.height = 300
                tableRow.addView(button, TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT))
            }
            table.addView(tableRow, TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT))
        }






//        val intent = Intent(this, EndActivity::class.java)
//        intent.putExtra("SCORE", 4)
//        startActivity(intent)
    }

    var ind = 0
    private val repeat = object : Runnable{override fun run(){
        val table = binding.tableLayout

        var row = table.getChildAt(ind/3) as TableRow
        var item = row.getChildAt(ind%3) as Button
        item.text = ""

        ind = Random.nextInt(0, 8)

        row = table.getChildAt(ind/3) as TableRow
        item = row.getChildAt(ind%3) as Button
        item.text = strBtn

        handler.postDelayed(this, 500)

    }
    }
    override fun onResume(){
        super.onResume()
        handler.post(repeat)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(repeat)
    }
    inner class Listener(x : Int, y : Int) : View.OnClickListener{

        override fun onClick(p0: View?) {
            var button = p0 as Button
            var tvScore = binding.tvCurScore
            if(button.text == strMole){
                res+=1
                button.text = ""
                tvScore.text = res.toString()

            }




        }

    }
}
