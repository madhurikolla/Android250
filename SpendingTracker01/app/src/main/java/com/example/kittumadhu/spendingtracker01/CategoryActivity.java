package com.example.kittumadhu.spendingtracker01;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {



    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    private EditText tvDisplayDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);



            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle extras=getIntent().getExtras();
        String value= (String) extras.get("Category");
        TextView CategoryField = (TextView) findViewById(R.id.textview03);
        CategoryField.setText(value);



        setCurrentDateOnView();
        addListenerOnButton();

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
                .append(day).append("/").append(month+1).append("/")
                .append(year).append(" "));

        // set current date into datepicker
        //dpResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        EditText btnChangeDate = (EditText) findViewById(R.id.textView2);

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


    @OnClick(R.id.button)
    public void onclickOk(View view){

        try {
            TextView categoryValue = (TextView) findViewById(R.id.textview03);

            String Category = categoryValue.getText().toString();

            Log.d("Category", Category);

            EditText selectedDate = (EditText) findViewById(R.id.textView2);


            String date01 = selectedDate.getText().toString();

            Log.d("Date", date01);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


            Date date = dateFormat.parse(date01);

            EditText expense = (EditText) findViewById(R.id.textView3);
            int Amount = Integer.parseInt(expense.getText().toString());

             if(Amount == 0  ){


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




             } else {

                 DbHelper db = DbHelper.getInstance(this);




                 Log.d("Amount", String.valueOf(Amount));

                 db.addSession(date, Category, Amount);

                 List<DBExpense> expenseTable = db.getSessions();


                 Intent intent = new Intent(this, MainActivity.class);

                 startActivity(intent);

             }

            }catch(Exception ex){



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




            Log.e("exception", ex.getMessage());

            }

    }


 @OnClick(R.id.button2)
    public void onClickCancel(View view){


        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
