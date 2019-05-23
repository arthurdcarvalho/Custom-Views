package br.com.arthur.customviews.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import br.com.arthur.customviews.R
import br.com.arthur.customviews.components.CompostCustomView

class MainActivity : AppCompatActivity() {

    private lateinit var compostCustomView: CompostCustomView
    private lateinit var inputValueNoMask: TextView
    private lateinit var inputValueMask: TextView

    private lateinit var valueNoMask: String
    private lateinit var valueMask: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
    }

    private fun initComponents() {
        compostCustomView = findViewById(R.id.compostCustomView)
        inputValueNoMask = findViewById(R.id.inputValueNoMask)
        inputValueMask = findViewById(R.id.inputValueMask)

        compostCustomView.setButtonSendListener(View.OnClickListener {
            valueNoMask = compostCustomView.getInputWithNoMask()
            valueMask = compostCustomView.getInputWithMask()

            inputValueNoMask.text = valueNoMask
            inputValueMask.text = valueMask
        })
    }

}