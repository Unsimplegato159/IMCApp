package com.santiagotorres.imcapp.ui.imc

import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import kotlin.math.pow

class IMCViewModel : ViewModel(){


    val Ft_to_In : Int = 12
    private val df = DecimalFormat("###.##")


    val emptyText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val imc: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val checkEmptyText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val switchText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val switchHeight: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val switchWeight: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val comparision_text: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val print_value: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }



    fun calcularImc(
        Height: String,
        Weight: String,
        totalView: LinearLayout,
        checked: Boolean,

    ) {

        if (Height.isEmpty() || Weight.isEmpty() ||
            (Height.isEmpty() && Weight.isEmpty()) ||
            (Height=="0")   ||    (Weight=="0")
        )
        {
            emptyText.value = "Tiene campos vacíos o algún campo en 0"
            Snackbar.make(totalView, emptyText.value.toString(), Snackbar.LENGTH_INDEFINITE).setAction("Aceptar"){}.show()
            imc.value = 0.0
        }
        else {
            if (checked){
                imc.value = (Weight.toDouble()/((Height.toDouble()*Ft_to_In).pow(2)))*703
            }
            else{
                imc.value = Weight.toDouble()/Height.toDouble().pow(2)
            }
        }
    }

    fun cleanswitch(checked: Boolean) {
        if (checked){
            checkEmptyText.value = ""
        }
    }

    fun checkswitch(checked: Boolean) {
        if (checked){
            switchText.value = "Desactivar sistema inglés"
        }
        else{
            switchText.value = "Activar sistema inglés"
        }
    }

    fun checkheight(checked: Boolean) {
        if (checked){
            switchHeight.value = "Estatura: Ft"
        }
        else{
            switchHeight.value = "Estatura: M"
        }
    }

    fun checkweight(checked: Boolean) {
        if (checked){
            switchWeight.value = "Peso: Lb"
        }
        else{
            switchWeight.value = "Peso: Kg"
        }
    }

    fun comparacion(IMC: Double) {
        if (IMC in 0.1..18.5) {
            comparision_text.value = "Bajo peso"
        } else if (IMC in 18.5..24.99) {
            comparision_text.value = "Peso saludable"
        } else if (IMC in 25.0..29.99) {
            comparision_text.value = "Sobrepeso"
        } else if (IMC > 30.0) {
            comparision_text.value = "Obesidad"
        } else if (IMC == 0.0) {
            comparision_text.value = ""
        }
        else{
            comparision_text.value = ""
        }
    }

    fun imprimir(imc: Double) {
        if (imc != 0.0){
            print_value.value = "Tu IMC es de ${df.format(imc)}"
        }
        else {
            print_value.value = ""
        }
    }


}