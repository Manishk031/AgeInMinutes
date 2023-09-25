package com.manish.agetomincalculater

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale
import java.util.SimpleTimeZone

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

      private var tvSelected : TextView? = null

    private var tvMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDate : Button = findViewById(R.id.btnDate)
        tvSelected = findViewById(R.id.tvSelected)
        tvMinutes = findViewById((R.id.tvMinutes))

        btnDate.setOnClickListener{
            clickDatePicker()
        }
    }

     private fun clickDatePicker()
     {   val myCalendar = Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)
         val dpd = DatePickerDialog(this,
             DatePickerDialog.OnDateSetListener{_x  ,selectedYear,selectedMonth,
                                                selectedDayOfMonth->
                 Toast.makeText(this,
                     "Year $selectedYear , month ${selectedMonth+1}"+
                             "day $selectedDayOfMonth",
                     Toast.LENGTH_LONG).show()

                 val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDayOfMonth"
                 tvSelected?.text = selectedDate

                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                 val theDate =sdf.parse(selectedDate)
                 theDate?.let {
                     val selectedDateInMinutes = theDate.time / 60000

                     val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                     currentDate?.let {
                         val currentDateInMinutes = currentDate.time/60000

                         val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                         tvMinutes?.text = differenceInMinutes.toString()

                     }

                      }


             },
             year,
             month,
             day
         )
         dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


     }
}