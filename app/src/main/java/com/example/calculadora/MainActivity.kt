package com.example.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.igual.setOnClickListener {

            val resultado = Expression(calculo.text.toString()).calculate()
            if (resultado.isNaN()) {
                calculo.text = "0"
            } else {
                calculo.text = resultado.toString()
            }
        }

        // Apagar
        binding.ce.setOnClickListener {
            calculo.text = "0"
        }
        binding.apagar.setOnClickListener {
            if (calculo.text.length.equals(1)) {
                calculo.text = "0"
            } else {
                calculo.text = calculo.text.dropLast(1).toString()
            }
        }

        // Operações
        binding.parenteseAbrindo.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = "("
            } else {
                calculo.text = "${calculo.text}("
            }
        }
        binding.parenteseFechando.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ")"
            } else {
                calculo.text = "${calculo.text})"
            }
        }
        binding.divisao.setOnClickListener {
            val ultimoChar = calculo.text.toString().lastOrNull()
            if (ultimoChar in operadores) {
                return@setOnClickListener
            }

            calculo.text = "${calculo.text}/"
        }
        binding.multiplicacao.setOnClickListener {
            val ultimoChar = calculo.text.toString().lastOrNull()
            if (ultimoChar in operadores) {
                return@setOnClickListener
            }

            calculo.text = "${calculo.text}*"
        }
        binding.subtracao.setOnClickListener {
            val ultimoChar = calculo.text.toString().lastOrNull()
            if (ultimoChar in operadores) {
                return@setOnClickListener
            }

            calculo.text = "${calculo.text}-"
        }
        binding.adicao.setOnClickListener {
            val ultimoChar = calculo.text.toString().lastOrNull()
            if (ultimoChar in operadores) {
                return@setOnClickListener
            }

            calculo.text = "${calculo.text}+"
        }

        // Números
        binding.virgula.setOnClickListener {
            val expressao: String = binding.calculo.text.toString()

            if (expressao.lastOrNull() in operadores) {
                return@setOnClickListener
            }

            val expressaoAtual = expressao.lastIndexOfAny(charArrayOf('+', '-', '/', '*', ')', '('))

            if (expressao.substring(expressaoAtual + 1).contains('.')) {
                return@setOnClickListener
            }

            calculo.text = "${calculo.text}."
        }

        binding.zero.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = "0"
            } else {
                calculo.text = "${calculo.text}0"
            }
        }
        binding.um.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}1"
        }
        binding.dois.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}2"
        }
        binding.tres.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}3"
        }
        binding.quatro.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}4"
        }
        binding.cinco.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}5"
        }
        binding.seis.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}6"
        }
        binding.sete.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}7"
        }
        binding.oito.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}8"
        }
        binding.nove.setOnClickListener {
            if (calculo.text.equals("0")) {
                calculo.text = ""
            }
            calculo.text = "${calculo.text}9"
        }


    }
}