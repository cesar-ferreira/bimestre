package com.cesar.planilhadenotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_result.*
import java.io.Console
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private var average1: Double = 0.0
    private var average1Rounded: Int = 0

    private var average2: Double = 0.0
    private var average2Rounded: Int = 0

    private var average3: Double = 0.0
    private var average3Rounded: Int = 0

    private var average4: Double = 0.0
    private var average4Rounded: Int = 0


    private var result = ""

    private var averageTotal: Double = 0.0
    private var averageTotalRounded: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInterface()

    }

    private fun initInterface() {
        checkbox()
        click()
    }

    private fun click() {
        buttonCalculate.setOnClickListener(this)
        buttonClean.setOnClickListener(this)

    }

    private fun checkbox() {
        checkBox1AverageRecovery.setOnCheckedChangeListener(this)
        checkBox2AverageRecovery.setOnCheckedChangeListener(this)
        checkBox3AverageRecovery.setOnCheckedChangeListener(this)
        checkBox4AverageRecovery.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(view: CompoundButton, value: Boolean) {

        when (view.id) {
            R.id.checkBox1AverageRecovery -> isRecovery(checkBox1AverageRecovery.isChecked, 1)
            R.id.checkBox2AverageRecovery -> isRecovery(checkBox2AverageRecovery.isChecked, 2)
            R.id.checkBox3AverageRecovery -> isRecovery(checkBox3AverageRecovery.isChecked, 3)
            R.id.checkBox4AverageRecovery -> isRecovery(checkBox4AverageRecovery.isChecked, 4)

        }
    }

    private fun createDialogClean () {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("Limpar campos de texto")

        // Display a message on alert dialog
        builder.setMessage("Você deseja realmente limpar todos os campos de texto digitados?")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES"){dialog, which ->
            cleanForm()
        }


        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){dialog,which ->
            dialog.dismiss()
        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun createDialogCalculate() {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_result, null)

        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)

        builder.setView(mDialogView)

        // Set the alert dialog title
        builder.setTitle("Resultado")

        // Display a message on alert dialog
//        builder.setMessage("Você deseja realmente limpar todos os campos de texto digitados?")

        // Display a neutral button on alert dialog
        builder.setNeutralButton("Ok"){dialog,_ ->
            dialog.dismiss()
        }


        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()

        initResultDialog(dialog)

    }

    private fun initResultDialog(dialog: AlertDialog) {

        dialog.textView1AverageValue.text = average1.toString()
        dialog.textView1AverageRoundedValue.text = average1Rounded.toString()

        dialog.textView2AverageValue.text = average2.toString()
        dialog.textView2AverageRoundedValue.text = average2Rounded.toString()

        dialog.textView3AverageValue.text = average3.toString()
        dialog.textView3AverageRoundedValue.text = average3Rounded.toString()

        dialog.textView4AverageValue.text = average4.toString()
        dialog.textView4AverageRoundedValue.text = average4Rounded.toString()

        dialog.textViewAverageTotalValue.text = averageTotal.toString()
        dialog.textViewAverageTotalRoundedValue.text = averageTotalRounded.toString()

        dialog.textViewResult.text = result

    }

    private fun isRecovery(value: Boolean, average: Int) {

        when (average) {
            1 -> {
                if (value) {
                    hideAverage1()
                    cleanFirstAverage()

                } else {
                    hideRecorevy1()
                    cleanFirstRecovery()
                }
            }
            2 -> {
                if (value) {
                    hideAverage2()
                    cleanSecondAverage()

                } else {
                    hideRecorevy2()
                    cleanSecondRecovery()
                }
            }
            3 -> {
                if (value) {
                    hideAverage3()
                    cleanThirdAverage()

                } else {
                    hideRecorevy3()
                    cleanThirdRecovery()
                }
            }
            4 -> {
                if (value) {
                    hideAverage4()
                    cleanFourthAverage()

                } else {
                    hideRecorevy4()
                    cleanFourthRecovery()
                }
            }
        }
    }

    override fun onClick(view: View) {

        when(view.id) {
            R.id.buttonCalculate -> {
                cleanVariablesCalculate()
                calculate()
                createDialogCalculate()

            }
            R.id.buttonClean -> createDialogClean()
        }

    }

    private fun cleanVariablesCalculate() {

        average1 = 0.0
        average1Rounded = 0

        average2 = 0.0
        average2Rounded = 0

        average3 = 0.0
        average3Rounded = 0

        average4 = 0.0
        average4Rounded = 0


        result = ""

        averageTotal = 0.0
        averageTotalRounded = 0


    }

    private fun calculate() {
        calculateFirstAverage()
        calculateSecondAverage()
        calculateThirdAverage()
        calculateFourthAverage()
        calculateAverageTotal()
    }

    private fun calculateFirstAverage() {

        if (editText1Average1Note.text.isNotEmpty() &&
            editText1Average2Note.text.isNotEmpty()) {

            average1 = ( editText1Average1Note.text.toString().toDouble() + editText1Average2Note.text.toString().toDouble() ) / 2
            average1Rounded = average1.roundToInt()


        } else if(editText1AverageRecoveryNote.text.isNotEmpty()) {

            average1 = editText1AverageRecoveryNote.text.toString().toDouble()
            average1Rounded = average1.roundToInt()

        } else {

            average1 = 0.0
            average1Rounded = average1.roundToInt()

        }

        averageTotal += average1

    }


    private fun calculateSecondAverage() {

        if (editText2Average1Note.text.isNotEmpty() &&
            editText2Average2Note.text.isNotEmpty()) {

            average2 = ( editText2Average1Note.text.toString().toDouble() + editText2Average2Note.text.toString().toDouble() ) / 2
            average2Rounded = average2.roundToInt()


        } else if(editText2AverageRecoveryNote.text.isNotEmpty()) {

            average2 = editText2AverageRecoveryNote.text.toString().toDouble()
            average2Rounded = average2.roundToInt()

        } else {

            average2 = 0.0
            average2Rounded = average2.roundToInt()

        }

        averageTotal += average2

    }

    private fun calculateThirdAverage() {

        if (editText3Average1Note.text.isNotEmpty() &&
            editText3Average2Note.text.isNotEmpty()) {

            average3 = ( editText3Average1Note.text.toString().toDouble() + editText3Average2Note.text.toString().toDouble() ) / 2
            average3Rounded = average3.roundToInt()


        } else if(editText3AverageRecoveryNote.text.isNotEmpty()) {

            average3 = editText3AverageRecoveryNote.text.toString().toDouble()
            average3Rounded = average3.roundToInt()

        } else {

            average3 = 0.0
            average3Rounded = average3.roundToInt()

        }

        averageTotal += average3

    }

    private fun calculateFourthAverage() {

        if (editText4Average1Note.text.isNotEmpty() &&
            editText4Average2Note.text.isNotEmpty()) {

            average4 = ( editText4Average1Note.text.toString().toDouble() + editText4Average2Note.text.toString().toDouble() ) / 2
            average4Rounded = average4.roundToInt()


        } else if(editText4AverageRecoveryNote.text.isNotEmpty()) {

            average4 = editText4AverageRecoveryNote.text.toString().toDouble()
            average4Rounded = average4.roundToInt()

        } else {

            average4 = 0.0
            average4Rounded = average4.roundToInt()

        }

        averageTotal += average4

    }

    private fun calculateAverageTotal() {

        averageTotal = (averageTotal / 4)
        averageTotalRounded = averageTotal.roundToInt()

        if (averageTotal < 4) {
            result = "Reprovado"
        } else if (averageTotal >= 7) {
            result = "Aprovado"

        } else {
            result = "Final"
        }

    }





    private fun cleanForm() {

        // Limpa cmapos do primeiro bimestre
        cleanFirstAverage()
        cleanFirstRecovery()
        checkBox1AverageRecovery.isChecked = false

        // Limpa cmapos do segundo bimestre
        cleanSecondAverage()
        cleanSecondRecovery()
        checkBox2AverageRecovery.isChecked = false

        // Limpa cmapos do terceiro bimestre
        cleanThirdAverage()
        cleanThirdRecovery()
        checkBox3AverageRecovery.isChecked = false

        // Limpa cmapos do quarto bimestre
        cleanFourthAverage()
        cleanFourthRecovery()
        checkBox4AverageRecovery.isChecked = false

    }



    private fun cleanFirstAverage() {
        editText1Average1Note.setText("")
        editText1Average2Note.setText("")
    }

    private fun cleanFirstRecovery() {
        editText1AverageRecoveryNote.setText("")
    }

    private fun cleanSecondAverage() {
        editText2Average1Note.setText("")
        editText2Average2Note.setText("")
    }

    private fun cleanSecondRecovery() {
        editText2AverageRecoveryNote.setText("")
    }

    private fun cleanThirdAverage() {
        editText3Average1Note.setText("")
        editText3Average2Note.setText("")
    }

    private fun cleanThirdRecovery() {
        editText3AverageRecoveryNote.setText("")
    }

    private fun cleanFourthAverage() {
        editText4Average1Note.setText("")
        editText4Average2Note.setText("")
    }

    private fun cleanFourthRecovery() {
        editText4AverageRecoveryNote.setText("")
    }



    private fun hideRecorevy1() {
        editText1AverageRecoveryNote.visibility = View.GONE

        editText1Average1Note.visibility = View.VISIBLE
        editText1Average2Note.visibility = View.VISIBLE
    }

    private fun hideAverage1() {
        editText1AverageRecoveryNote.visibility = View.VISIBLE

        editText1Average1Note.visibility = View.GONE
        editText1Average2Note.visibility = View.GONE
    }

    private fun hideRecorevy2() {
        editText2AverageRecoveryNote.visibility = View.GONE

        editText2Average1Note.visibility = View.VISIBLE
        editText2Average2Note.visibility = View.VISIBLE
    }

    private fun hideAverage2() {
        editText2AverageRecoveryNote.visibility = View.VISIBLE

        editText2Average1Note.visibility = View.GONE
        editText2Average2Note.visibility = View.GONE
    }

    private fun hideRecorevy3() {
        editText3AverageRecoveryNote.visibility = View.GONE

        editText3Average1Note.visibility = View.VISIBLE
        editText3Average2Note.visibility = View.VISIBLE
    }

    private fun hideAverage3() {
        editText3AverageRecoveryNote.visibility = View.VISIBLE

        editText3Average1Note.visibility = View.GONE
        editText3Average2Note.visibility = View.GONE
    }

    private fun hideRecorevy4() {
        editText4AverageRecoveryNote.visibility = View.GONE

        editText4Average1Note.visibility = View.VISIBLE
        editText4Average2Note.visibility = View.VISIBLE
    }

    private fun hideAverage4() {
        editText4AverageRecoveryNote.visibility = View.VISIBLE

        editText4Average1Note.visibility = View.GONE
        editText4Average2Note.visibility = View.GONE
    }

}
