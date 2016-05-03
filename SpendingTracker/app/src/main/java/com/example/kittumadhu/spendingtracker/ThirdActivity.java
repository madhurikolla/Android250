package com.example.kittumadhu.spendingtracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.security.KeyStore;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThirdActivity extends AppCompatActivity {
    private int year;
    private int month;
    private int day;

    private TextView tvDisplayDate;

    static final int DATE_DIALOG_ID = 999;






//

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



        setCurrentDateOnView();
        addListenerOnButton();




        //textView=dbExpense.get(0).toString();
    }




    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.summaryPoints);
        // dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month+1).append("/")
                .append(year).append(" "));

        // set current date into datepicker
        //dpResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        Button btnChangeDate = (Button) findViewById(R.id.viewByDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(day )
                    .append("/").append(month+1).append("/").append(year)
                    .append(" "));


        }
    };


    public void selectedDate(View view){
       try {

           TextView choosenDate = (TextView) findViewById(R.id.summaryPoints);

           String date01 = choosenDate.getText().toString();

           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


           Date date = dateFormat.parse(date01);


           DbHelper db = DbHelper.getInstance(this);

           List<DBExpense> dbExpensebyDate = db.getByDate(date);



           if(dbExpensebyDate.size()==0 || dbExpensebyDate.isEmpty()){

               final Dialog dialog = new Dialog(this);

               dialog.setContentView(R.layout.activity_dialog_box);

               dialog.setTitle("No Data Found!");


               Button okButton = (Button)dialog.findViewById(R.id.dialog_ok);
               okButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       dialog.dismiss();
                   }
               });

               dialog.show();

            }else {

               Intent myMainActivity = new Intent(this, PieChartActivity.class);
               Bundle b = new Bundle();
               int total = dbExpensebyDate.get(0).getFood() + dbExpensebyDate.get(0).getTravel() + dbExpensebyDate.get(0).getShopping() +
                       dbExpensebyDate.get(0).getMisc();

               b.putString("Food", String.valueOf(dbExpensebyDate.get(0).getFood()));
               b.putString("Travel", String.valueOf(dbExpensebyDate.get(0).getTravel()));
               b.putString("Shopping", String.valueOf(dbExpensebyDate.get(0).getShopping()));
               b.putString("Misc", String.valueOf(dbExpensebyDate.get(0).getMisc()));
               b.putString("Total", String.valueOf(total));
               myMainActivity.putExtras(b);
               startActivity(myMainActivity);

           }
         }catch(ParseException ex){
        Log.e("parsing exception",ex.getMessage());
        }



    }
}
