package com.example.kittumadhu.spendingtracker01;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ViewExpensesActivity extends AppCompatActivity {


    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID01 = 899;
    private EditText tvDisplayDate;
    private EditText tvDisplayDate01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Spinner spinner = (Spinner) findViewById(R.id.phonetype_spinner);
//
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.phonetype_array, android.R.layout.simple_spinner_item);
//
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);




        setCurrentDateOnView();


        addListenerOnButton();
        setCurrenttoDateOnView();
        addListenerToDateOnButton();
        ButterKnife.bind(this);

    }


    public void setCurrentDateOnView() {

        tvDisplayDate = (EditText) findViewById(R.id.textView2);
        // dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month + 1).append("/")
                .append(year).append(" "));

//        tvDisplayDate01.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(day).append("/").append(month+1).append("/")
//                .append(year).append(" "));


        // set current date into datepicker
        //dpResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        EditText btnChangeDate = (EditText) findViewById(R.id.textView2);
        //EditText btnChangeDate01 = (EditText) findViewById(R.id.textView3);


        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    public void setCurrenttoDateOnView() {


        // dpResult = (DatePicker) findViewById(R.id.dpResult);
        tvDisplayDate01 = (EditText) findViewById(R.id.textView3);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview


        tvDisplayDate01.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month + 1).append("/")
                .append(year).append(" "));


        // set current date into datepicker
        //dpResult.init(year, month, day, null);

    }

    public void addListenerToDateOnButton() {


        EditText btnChangeDate01 = (EditText) findViewById(R.id.textView3);

        btnChangeDate01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID01);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener01,
                        year, month, day);

            case DATE_DIALOG_ID01:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener01
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));


        }
    };


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

//            // set selected date into textview
//            tvDisplayDate.setText(new StringBuilder().append(day )
//                    .append("/").append(month+1).append("/").append(year)
//                    .append(" "));

            tvDisplayDate01.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));


        }
    };


    @OnClick(R.id.button4)
    public void onClickOk(View view) {


        try {
            EditText fromDate = (EditText) findViewById(R.id.textView2);
            EditText toDate = (EditText) findViewById(R.id.textView3);

            String date01 = fromDate.getText().toString();

            Log.d("Date", date01);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


            Date fromdate = dateFormat.parse(date01);


            String date02 = toDate.getText().toString();

            Log.d("Date", date02);
            DateFormat dateFormat01 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


            Date todate = dateFormat.parse(date02);

            DbHelper db = DbHelper.getInstance(this);
            List<DBExpense> dbexpenses;
            ArrayList<String> Category = new ArrayList<>();
            ArrayList<Integer> Amount = new ArrayList<>();

            if (fromdate.equals(todate)) {

                dbexpenses = db.getByDate(fromdate);

                for (int j = 0; j < dbexpenses.size(); j++) {
                    Log.d("Category", dbexpenses.get(j).getCategory());
                    Category.add(dbexpenses.get(j).getCategory());

                    Log.d("Amount", String.valueOf(dbexpenses.get(j).getAmount()));
                    Amount.add(dbexpenses.get(j).getAmount());
                }

            } else {
                dbexpenses = db.getfromDate(fromdate, todate);

                for (int j = 0; j < dbexpenses.size(); j++) {
                    Log.d("Category", dbexpenses.get(j).getCategory());
                    Category.add(dbexpenses.get(j).getCategory());

                    Log.d("Amount", String.valueOf(dbexpenses.get(j).getAmount()));

                    Amount.add(dbexpenses.get(j).getAmount());
                }
            }


            if (dbexpenses.size() == 0) {

                final Dialog dialog = new Dialog(this);

                dialog.setContentView(R.layout.activity_dialog_box);

                dialog.setTitle("No Data Found!");


                Button okButton = (Button) dialog.findViewById(R.id.dialog_ok);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();

            } else {


                Intent myMainActivity = new Intent(this, PieChartActivity.class);
                Bundle b = new Bundle();

                b.putStringArrayList("Categories", Category);
                b.putIntegerArrayList("Amount", Amount);
                b.putString("FromDate", date01);
                b.putString("ToDate", date02);
                myMainActivity.putExtras(b);
                startActivity(myMainActivity);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @OnClick(R.id.button5)
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
