package com.example.kittumadhu.spendingtracker01;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;

import java.util.ArrayList;
import java.util.Arrays;

public class BarGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargraph);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras=getIntent().getExtras();
       String[] Category= extras.getStringArray("Category");
        int [] amount=extras.getIntArray("Amount");

        for(int i=0;i<Category.length;i++){
            Log.d("Xdata",Category[i]);

        }
        for(int i=0;i<amount.length;i++){
            Log.d("Ydata", String.valueOf(amount[i]));

        }

        ArrayList<String> category01= new ArrayList<String>(Arrays.asList(Category));

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        int j=0;

        for(int i=0;i<amount.length;i++) {
            BarEntry v1e1 = new BarEntry(amount[i], j); // Jan
            valueSet1.add(v1e1);
            j++;
        }
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "AmountSpent");
        barDataSet1.setColor(Color.rgb(0, 155, 0));



        BarChart chart = (BarChart) findViewById(R.id.chart);

//        BarData data = new BarData(getXAxisValues());
        BarData data01 = new BarData(category01, barDataSet1);

        chart.setData(data01);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
//        chart.setScrollY(chart.getHighestVisibleXIndex());



        chart.invalidate();


    }





}
