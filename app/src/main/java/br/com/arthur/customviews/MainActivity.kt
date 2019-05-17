package br.com.arthur.customviews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import br.com.arthur.customviews.views.CompostCustomView

class MainActivity : AppCompatActivity() {

    private lateinit var compostCustomView: CompostCustomView
    private lateinit var inputValue: TextView

    private lateinit var value: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
    }

    private fun initComponents() {
        compostCustomView = findViewById(R.id.compostCustomView)
        inputValue = findViewById(R.id.inputValue)

        compostCustomView.setButtonSendListener(View.OnClickListener {
            value = compostCustomView.getInputWithNoMask()
            inputValue.text = value
        })
    }

}