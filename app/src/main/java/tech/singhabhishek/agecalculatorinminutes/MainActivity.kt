package tech.singhabhishek.agecalculatorinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.btndatepicker)
        button.setOnClickListener {view->
            ClickDatepicker(view)
        }
    }

    fun ClickDatepicker(view: View) {
        val mycalender= Calendar.getInstance()
        val year=mycalender.get(Calendar.YEAR)
        val month=mycalender.get(Calendar.MONTH)
        val date=mycalender.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{
                    view,Selectedyear,Selectedmonth,SelecteddayofMonth ->
                    Toast.makeText(this,"Congratulations You are ${Calendar.getInstance().get(Calendar.YEAR)-Selectedyear} Years Old", Toast.LENGTH_LONG).show()
                    val selectedDate="$SelecteddayofMonth/${Selectedmonth+1}/$Selectedyear"

                    val tvselectdate=findViewById<TextView>(R.id.tvSelectDate)
                    tvselectdate.setText(selectedDate)

                    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val thedate=sdf.parse(selectedDate)

                    val selectedDateinMinutes=thedate!!.time/60000

                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDatetoMinutes=currentDate!!.time/60000

                    val differenceinMinutes=currentDatetoMinutes-selectedDateinMinutes

                    val ResultedDateinminutes=findViewById<TextView>(R.id.tvSelectDateinMinutes)
                    ResultedDateinminutes.setText(differenceinMinutes.toString())
                }
                ,year
                ,month
                ,date)

        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }
}