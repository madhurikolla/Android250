package com.example.kittumadhu.spendingtracker01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
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
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BarGraphActivity extends AppCompatActivity implements  OnChartValueSelectedListener  {


    BarChart chart;
    String[] Category;
    int [] amount;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargraph);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras=getIntent().getExtras();
        Category = extras.getStringArray("Category");
        amount=extras.getIntArray("Amount");
        desc=extras.getString("Description");

        Log.d("Description" ,desc);




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

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Categories");
        barDataSet1.setColor(Color.rgb(0, 155, 0));


        chart = (BarChart) findViewById(R.id.chart);

        BarData data01 = new BarData(category01, barDataSet1);
        data01.setValueTextSize(10f);
        data01.setHighlightEnabled(true);
        chart.setData(data01);
        chart.setDescription(desc);
        chart.animateXY(500,500);
//        chart.setScrollY(chart.getHighestVisibleXIndex());
        chart.setOnChartValueSelectedListener(this);

        chart.setDrawValueAboveBar(true);
       // chart.invalidate();


    //    chart.setOnChartValueSelectedListener(this);

    }




    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {


        if (e == null)
            return;

        Toast.makeText(BarGraphActivity.this,
                Category[e.getXIndex()] + " = " + " $ " + e.getVal(), Toast.LENGTH_SHORT).show();

//        RectF bounds = chart.getBarBounds((BarEntry) e);
//        PointF position = chart.getPosition(e, YAxis.AxisDependency.LEFT);
//
//        Log.i("bounds", bounds.toString());
//        Log.i("position", position.toString());
//
//        Log.i("x-index",
//                "low: " + chart.getLowestVisibleXIndex() + ", high: "
//                        + chart.getHighestVisibleXIndex());

    }

    @Override
    public void onNothingSelected() {

    }
}
