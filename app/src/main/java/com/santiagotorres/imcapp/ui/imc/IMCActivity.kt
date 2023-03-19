package com.santiagotorres.imcapp.ui.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.google.android.material.snackbar.Snackbar
import com.santiagotorres.imcapp.R
import com.santiagotorres.imcapp.databinding.ActivityImcactivityBinding
import java.text.DecimalFormat
import kotlin.math.pow

//Subido a branch version1.0 - Laboratorio #1
class IMCActivity : AppCompatActivity() {

    private lateinit var converterBinding: ActivityImcactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        converterBinding = ActivityImcactivityBinding.inflate(layoutInflater)

        val view = converterBinding.root
        setContentView(view)

        val switch = findViewById<Switch>(R.id.system_switch)

        var estaturam: Float
        var pesokg: Float
        var estaturaFt: Float
        var pesolibras: Float
        var imc: Float
        val df = DecimalFormat("###.##")

        converterBinding.calculateButton.setOnClickListener {

            if (converterBinding.editTextHeight.text?.isEmpty() == true ||
                converterBinding.editTextWeight.text?.isEmpty() == true ||
                (converterBinding.editTextHeight.text?.isEmpty() == true &&
                converterBinding.editTextWeight.text?.isEmpty() == true) ){

                Snackbar.make(converterBinding.totalView, "Ingrese los valores faltantes", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
            }
            else{
                estaturam = converterBinding.editTextHeight.text.toString().toFloat()
                pesokg = converterBinding.editTextWeight.text.toString().toFloat()
                imc = pesokg / estaturam.pow(2)

                if (estaturam == 0F && pesokg == 0F){
                    Snackbar.make(converterBinding.totalView, "Ingrese valores diferentes de 0", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){
                        converterBinding.editTextHeight.setText("")
                        converterBinding.editTextWeight.setText("")
                    }.show()
                }
                else{
                    converterBinding.resultText.text = "Tu IMC es de ${df.format(imc)}"
                }
                comparition(imc)
            }
        }

        switch.setOnCheckedChangeListener { _, _ ->
            if (!switch.isChecked) {
                converterBinding.resultText.setText("")
                converterBinding.comparition.setText("")
                switch.text = "Activar sistema inglés"

                converterBinding.heightMetric.text = "Estatura: M"
                converterBinding.weightMetric.text = "Peso: Kg"
                converterBinding.editTextHeight.setText("")
                converterBinding.editTextWeight.setText("")


                converterBinding.calculateButton.setOnClickListener {

                    if (converterBinding.editTextHeight.text?.isEmpty() == true ||
                        converterBinding.editTextWeight.text?.isEmpty() == true ||
                        (converterBinding.editTextHeight.text?.isEmpty() == true &&
                                converterBinding.editTextWeight.text?.isEmpty() == true) ){

                        Snackbar.make(converterBinding.totalView, "Ingrese los valores faltantes", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
                    }
                    else {

                        estaturam = converterBinding.editTextHeight.text.toString().toFloat()
                        pesokg = converterBinding.editTextWeight.text.toString().toFloat()

                        imc = pesokg / estaturam.pow(2)

                        if (estaturam == 0F && pesokg == 0F){
                            Snackbar.make(converterBinding.totalView, "Ingrese valores diferentes de 0", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){
                                converterBinding.editTextHeight.setText("")
                                converterBinding.editTextWeight.setText("")

                            }.show()
                        }
                        else{
                            converterBinding.resultText.text = "Tu IMC es de ${df.format(imc)}"
                        }
                        comparition(imc)
                    }
                }
            }

            else {
                converterBinding.resultText.setText("")
                converterBinding.comparition.setText("")

                switch.text = "Desactivar sistema inglés"
                val Ft_to_In : Int = 12

                converterBinding.heightMetric.text = "Estatura: Ft"
                converterBinding.weightMetric.text = "Peso: Lb"
                converterBinding.editTextHeight.setText("")
                converterBinding.editTextWeight.setText("")

                converterBinding.calculateButton.setOnClickListener {

                    if (converterBinding.editTextHeight.text?.isEmpty() == true ||
                        converterBinding.editTextWeight.text?.isEmpty() == true ||
                        (converterBinding.editTextHeight.text?.isEmpty() == true &&
                                converterBinding.editTextWeight.text?.isEmpty() == true) ){

                        Snackbar.make(converterBinding.totalView, "Ingrese los valores faltantes", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
                    }
                    else {

                        estaturaFt = converterBinding.editTextHeight.text.toString().toFloat() * Ft_to_In
                        pesolibras = converterBinding.editTextWeight.text.toString().toFloat()

                        imc = (pesolibras / (estaturaFt.pow(2))) * 703

                        if (estaturaFt == 0F && pesolibras == 0F){
                            Snackbar.make(converterBinding.totalView, "Ingrese valores diferentes de 0", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){
                                converterBinding.editTextHeight.setText("")
                                converterBinding.editTextWeight.setText("")

                            }.show()
                        }
                        else{
                            converterBinding.resultText.text = "Tu IMC es de ${df.format(imc)}"
                        }
                        comparition(imc)
                    }
                }
            }
        }
    }

    private fun comparition(IMC: Float) {
        if (IMC < 18.5) {
            converterBinding.comparition.text = "Bajo peso"
        } else if (IMC in 18.5..24.99) {
            converterBinding.comparition.text = "Peso saludable"
        } else if (IMC in 25.0..29.99) {
            converterBinding.comparition.text = "Sobrepeso"
        } else if (IMC > 30.0) {
            converterBinding.comparition.text = "Obesidad"
        }
    }
}