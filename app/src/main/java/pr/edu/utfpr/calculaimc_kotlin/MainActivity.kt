package pr.edu.utfpr.calculaimc_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }
    }

    private fun btLimparOnClick() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.text = getString(R.string.zeros)
        etPeso.requestFocus()
    }

    private fun btCalcularOnClick() {
        if (etPeso.text.toString().isEmpty()) {
            etPeso.error = getString(R.string.erro_peso)
            etPeso.requestFocus()
            return
        }

        if (etAltura.text.toString().isEmpty()) {
            etAltura.error = getString(R.string.erro_altura)
            etAltura.requestFocus()
            return
        }

        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc = Calculo().calculaIMC(peso, altura)
        tvResultado.text = imc.toString()
    }

    class Calculo {
        fun calculaIMC(peso: Double, altura: Double): Double {
            var imc = 0.0
            var numberFormat: NumberFormat?

//        Exemplo com tratamento de language
//        if (Locale.getDefault().language.equals("en")) {
//            imc = ((703 * peso) / altura.pow(2))
//            numberFormat = NumberFormat.getNumberInstance(Locale.US)
//        } else {
//            imc = peso / altura.pow(2)
//            numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
//        }
//        return numberFormat.format(imc).toDouble()


//        Exemplo sem tratamento de language
            imc = peso / altura.pow(2)
            numberFormat = NumberFormat.getNumberInstance(Locale.US)
            return numberFormat.format(imc).toDouble()
        }
    }
}