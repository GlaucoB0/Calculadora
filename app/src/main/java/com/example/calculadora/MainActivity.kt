package com.example.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculo = binding.calculo
        val operadores = listOf('*', '-', '+', '/', '.')
        val operacaoIds = listOf("multiplicacao", "subtracao","adicao","divisao")
        var numeros = listOf("zero", "um", "dois", "tres", "quatro", "cinco", "seis", "sete", "oito", "nove")

        fun adicionarOperador(operador: String) {
            val ultimoChar = calculo.text.toString().lastOrNull()
            if (ultimoChar in operadores) {
                return
            }
            calculo.text = "${calculo.text}$operador"
        }

        fun adicionarNumero(numero: String) {
            if (calculo.text == "0") {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}$numero"
        }
        for (numero in numeros) {
            val buttonId = resources.getIdentifier(numero, "id", packageName)
            var btn = findViewById<AppCompatButton>(buttonId)
            btn.setOnClickListener {
                adicionarNumero(btn.text.toString())
            }
        }
        for (operacao in operacaoIds) {
            val buttonId = resources.getIdentifier(operacao, "id", packageName)
            var btn = findViewById<AppCompatButton>(buttonId)
            btn.setOnClickListener {
                adicionarOperador(btn.text.toString())
            }
        }

        binding.igual.setOnClickListener {
            var resultado = Expression(calculo.text.toString()).calculate().toString()

            if (resultado.contains('.') && resultado.endsWith(".0"))
                calculo.text = "${resultado.dropLast(2)}"
            else
                when (resultado) {
                    "NaN" -> calculo.text = "0"
                    else -> calculo.text = "${resultado}"
                }
        }
        // Apagar
        binding.ce.setOnClickListener {
            calculo.text = "0"
        }
        binding.apagar.setOnClickListener {
            if (calculo.text.length == 1) calculo.text = "0"
            else calculo.text = calculo.text.dropLast(1).toString()
        }
        // Parenteses
        binding.parenteseAbrindo.setOnClickListener {
            if (calculo.text == "0") calculo.text = "("
            else calculo.text = "${calculo.text}("
        }
        binding.parenteseFechando.setOnClickListener {
            if (!calculo.text.contains('(')) return@setOnClickListener
            else calculo.text = "${calculo.text})"
        }
        // Números
        binding.virgula.setOnClickListener {
            val expressao: String = binding.calculo.text.toString()

            if (expressao.last() in operadores) return@setOnClickListener

            val expressaoAtual = expressao.lastIndexOfAny(charArrayOf('+', '-', '/', '*', ')', '('))

            if (expressao.substring(expressaoAtual + 1).contains('.'))
                return@setOnClickListener
            calculo.text = "${calculo.text}."
        }
    }
}