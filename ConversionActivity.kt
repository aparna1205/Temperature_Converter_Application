package com.example.temperatureconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ConversionActivity : AppCompatActivity() {
    lateinit var convertBtn:Button
    lateinit var inpText:EditText
    lateinit var from:String
    lateinit var to:String
    lateinit var txtOp:TextView
    var value:Double=0.0
    var answer:Double=0.0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)


        convertBtn=findViewById(R.id.btn_convert)
        inpText=findViewById(R.id.edt_text_val)
        txtOp=findViewById(R.id.txtView_result)

        val languages = resources.getStringArray(R.array.Temperatures)

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)

        val autocompleteTVFrom = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewFrom)

        autocompleteTVFrom.setAdapter(arrayAdapter)
        val autocompleteTVTo = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTo)

        autocompleteTVTo.setAdapter(arrayAdapter)

        convertBtn.setOnClickListener(){
            value=inpText.text.toString().toDouble()
            from=autocompleteTVFrom.text.toString()
            to=autocompleteTVTo.text.toString()
            Toast.makeText(this,"Entered value converted from "+from+" "+to+" ",Toast.LENGTH_LONG).show()
            when(from){
                    "Fahrenheit (°F)" ->{
                        when(to){
                            "Celsius (°C)" ->{
                                answer =( (value  -  32  ) *  5)/9
                            }
                            "Kelvin (K)"->{
                                answer = 0.6*(value - 32) + 273.15
                            }
                        }

                    }
                    "Celsius (°C)" ->{
                        when(to){
                            "Fahrenheit (°F)" ->{
                                answer = ( value * 9/5) + 32
                            }
                            "Kelvin (K)"->{
                                answer = ( value + 273.15 )
                            }
                        }
                    }
                    "Kelvin (K)"->{
                        when(to){
                            "Fahrenheit (°F)" ->{
                                answer=1.8*(value-273) + 32
                            }
                            "Celsius (°C)"->{
                                answer = ( value - 273.15 )
                            }
                        }
                    }
            }
           // txtOp.setBackgroundColor(R.color.white)
            txtOp.setText(answer.toString())

        }
    }
}