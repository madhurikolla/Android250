package com.example.kittumadhu.spendingtracker01;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PieChartActivity extends AppCompatActivity {

    private FrameLayout mainLayout;
    private PieChart mChart;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pie:
                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_bar:
                Intent myMainActivity = new Intent(this, BarGraphActivity.class);

                Bundle b = new Bundle();
                b.putStringArray("Category",xData);
                b.putIntArray("Amount",yData);

                myMainActivity.putExtras(b);
                startActivity(myMainActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    String [] xData = {"AutoExpenses","Clothes","EatingOut","EducationalExpenses",
            "Entertainment","Food","Gifts","Groceries","Kids","Misc","ProfessionalExpenses","Shopping","Sports",
            "Travel" };

    int[] yData = {0, 0, 0, 0, 0, 0,0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras=getIntent().getExtras();


            String FromDate=extras.getString("FromDate");


            String ToDate = extras.getString("ToDate");

            ArrayList<String> Category = extras.getStringArrayList("Categories");

            ArrayList<Integer> Amount = extras.getIntegerArrayList("Amount");

            for (int i = 0; i < Amount.size(); i++) {

                yData[i] = Amount.get(i);
            }

            for (int i = 0; i < Category.size(); i++) {
                xData[i] = Category.get(i);

            }

            mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
            mChart = new PieChart(this);
            // add pie chart to main layout
            mainLayout.addView(mChart);
            mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));


            // configure pie chart
            mChart.setUsePercentValues(true);



            if (FromDate.equals(ToDate)) {

                String date = FromDate;

                mChart.setDescription("Amount Spent on "+ date);

            }else {
                mChart.setDescription("Amount Spent between " + FromDate + "and " + ToDate);
            }
            // enable hole and configure
            mChart.setDrawHoleEnabled(true);
            mChart.setHoleColor(android.R.color.black);
            mChart.setHoleRadius(7);
            mChart.setTransparentCircleRadius(10);
            // enable rotation of the chart by touch
            mChart.setRotationAngle(0);
            mChart.setRotationEnabled(true);

           mChart.setDescriptionTextSize(12f);


            // set a chart value selected listener


            mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    // display msg when value selected
                    if (e == null)
                        return;

                    Toast.makeText(PieChartActivity.this,
                            xData[e.getXIndex()] + " = " + " $ " + e.getVal(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected() {

                }
            });

            // add data
            addData();

            // customize legends
            Legend l = mChart.getLegend();
            // l.setPosition(LegendPosition);
//        l.setXEntrySpace(15);
//        l.setYEntrySpace(20);
            l.setEnabled(false);


    }



    private void addData() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Amount Spent");
        dataSet.setSliceSpace(1);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();


        int c = ColorTemplate.rgb("#008080");
            colors.add(c);


        int c1=ColorTemplate.rgb("#DC143C");
        colors.add(c1);

        int c2=ColorTemplate.rgb("#00FA9A");
        colors.add(c2);

        int c3=ColorTemplate.rgb("#FF0000");
            colors.add(c3);



        int c4=ColorTemplate.rgb("#708090");
        colors.add(c4);

        int c5=ColorTemplate.rgb("#4682B4");
        colors.add(c5);

        int c6=ColorTemplate.rgb("#FFFF00");
        colors.add(c6);

        int c7=ColorTemplate.rgb("#2F4F4F");
        colors.add(c7);



        int c8=ColorTemplate.rgb("#BDB76B");
        colors.add(c8);

        int c9=ColorTemplate.rgb("#ADFF2F");
        colors.add(c9);

        int c10=ColorTemplate.rgb("#8B008B");
        colors.add(c10);

        int c11=ColorTemplate.rgb("#191970");
        colors.add(c11);

        int c12=ColorTemplate.rgb("#00FA9A");
        colors.add(c12);

        int c13=ColorTemplate.rgb("#FFEFD5");
        colors.add(c13);



        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }




}
