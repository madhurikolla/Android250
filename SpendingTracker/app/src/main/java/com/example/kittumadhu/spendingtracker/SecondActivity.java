package com.example.kittumadhu.spendingtracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SecondActivity extends FragmentActivity {
//    DatePickerFragment datePickerFragment = new DatePickerFragment();
//    @Override

    private int year;
    private int month;
    private int day;

    private TextView tvDisplayDate;

    static final int DATE_DIALOG_ID = 999;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //Text tvDisplayDate = (TextView) findViewById(R.id.tvDate);


        //tvDisplayDate.setText();

        setCurrentDateOnView();
        addListenerOnButton();




    }
//    public void showDatePickerDialog(View v) {
//
//       // datePickerFragment.show(getFragmentManager(), "datepicker");
//
//
//       // Toast.makeText(SecondActivity.this, "selected date :" + month + "/" + day + "/" + year, Toast.LENGTH_LONG).show();
//    }



    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
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

       Button btnChangeDate = (Button) findViewById(R.id.date);

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

    public void onClickOk(View view) {


        try{
            EditText foodExp= (EditText) findViewById(R.id.foodExp);

            int food =  Integer.parseInt(foodExp.getText().toString());
           // Log.i("food", ((String) food));

            EditText travelExp= (EditText) findViewById(R.id.travelExp);

            int  travel=  Integer.parseInt(travelExp.getText().toString());

           // Log.i("travel", travel.toString());

            EditText shoppingExp= (EditText) findViewById(R.id.shoppinExp);

            int shopping =  Integer.parseInt(shoppingExp.getText().toString());
          //  Log.i("shopping", shopping.toString());
            EditText miscExp= (EditText) findViewById(R.id.miscExp);

            int  misc =  Integer.parseInt(miscExp.getText().toString());
          //  Log.i("Misc", misc.toString());

            TextView choosenDate=(TextView) findViewById(R.id.tvDate);

            String date01 = choosenDate.getText().toString();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);


            Date date = dateFormat.parse(date01);

            Log.i("Date",date.toString());

            int total=food+travel+shopping+misc;

           // Log.i("TotalExp",);


            DbHelper db= DbHelper.getInstance(this);

            db.addSession(date, food, travel, shopping, misc);

            List<DBExpense> expenseTable=db.getSessions();

            Log.i("date info",String.valueOf(expenseTable.get(0).getDate()));

        }catch(Exception ex){
            Log.e("exception",ex.getMessage());
        }



        Intent myMainActivity = new Intent(this, MainActivity.class);
        startActivity(myMainActivity);


    }



}


