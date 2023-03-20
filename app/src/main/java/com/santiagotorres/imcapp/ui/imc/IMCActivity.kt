package com.santiagotorres.imcapp.ui.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santiagotorres.imcapp.R
import com.santiagotorres.imcapp.databinding.ActivityImcactivityBinding
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var converterBinding: ActivityImcactivityBinding
    private lateinit var imcViewModel: IMCViewModel

    private var imc: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        converterBinding = ActivityImcactivityBinding.inflate(layoutInflater)
        imcViewModel = ViewModelProvider(this)[IMCViewModel::class.java]

        val view = converterBinding.root
        setContentView(view)

        val switch = findViewById<Switch>(R.id.system_switch)

        CalculateAllIMC(switch)


        switch.setOnCheckedChangeListener { _, _ ->

            val switchclean = Observer<String> { result ->
                converterBinding.resultText.text = result
                converterBinding.comparition.text = result
                converterBinding.editTextHeight.setText(result)
                converterBinding.editTextWeight.setText(result)

            }
            imcViewModel.checkEmptyText.observe(this, switchclean)


            val switchtext = Observer<String> { result ->

                switch.text = result
            }
            imcViewModel.switchText.observe(this, switchtext)


            val switchtextheight = Observer<String> { result ->

                converterBinding.heightMetric.text = result
            }
            imcViewModel.switchHeight.observe(this, switchtextheight)

            val switchtextweight = Observer<String> { result ->

                converterBinding.weightMetric.text = result
            }
            imcViewModel.switchWeight.observe(this, switchtextweight)

            imcViewModel.cleanswitch(switch.isChecked)
            imcViewModel.checkswitch(switch.isChecked)
            imcViewModel.checkheight(switch.isChecked)
            imcViewModel.checkweight(switch.isChecked)


            CalculateAllIMC(switch)

        }
    }


    private fun CalculateAllIMC(switch: Switch) {
        converterBinding.calculateButton.setOnClickListener {

            val calcularValor = Observer<Double> { result ->
                imc = result
            }
            imcViewModel.imc.observe(this, calcularValor)


            val printvalue = Observer<String> { result ->
                converterBinding.resultText.text = result.toString()
            }
            imcViewModel.print_value.observe(this, printvalue)


            val comparision = Observer<String> { result ->
                converterBinding.comparition.text = result
            }
            imcViewModel.comparision_text.observe(this, comparision)


            imcViewModel.calcularImc(
                converterBinding.editTextHeight.text.toString(),
                converterBinding.editTextWeight.text.toString(),
                converterBinding.totalView,
                switch.isChecked,
            )

            imcViewModel.imprimir(imc)
            imcViewModel.comparacion(imc)
        }
    }
}

