package com.example.kittumadhu.spendingtracker01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        ScrollView scrollView = new ScrollView(this);
//        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
//        LinearLayout linearLayout = new LinearLayout(this);
//
//        horizontalScrollView.addView(linearLayout);
//
//        scrollView.addView(horizontalScrollView);
//
//        setContentView(scrollView);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.button1)
    public void enterExpenses(View view) {
        Toast.makeText(MainActivity.this, "enterDailyExpense", Toast.LENGTH_LONG).show();

        Intent mySecondActivity = new Intent(this, SecondActivity.class);
        startActivity(mySecondActivity);
    }



    @OnClick(R.id.button3)
    public void viewExpenses(View view) {
        Toast.makeText(MainActivity.this, "viewExpense", Toast.LENGTH_LONG).show();

        Intent mySecondActivity = new Intent(this, ViewExpensesActivity.class);
        startActivity(mySecondActivity);
    }
}
