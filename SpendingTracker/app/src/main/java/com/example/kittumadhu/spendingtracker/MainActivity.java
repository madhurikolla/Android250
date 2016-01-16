package com.example.kittumadhu.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void enterDailyExpense(View view) {
        Toast.makeText(MainActivity.this, "enterDailyExpense", Toast.LENGTH_LONG).show();

        Intent mySecondActivity = new Intent(this, SecondActivity.class);
        startActivity(mySecondActivity);
    }

    public void viewExpenses(View view){

        Intent myThirdActivity = new Intent(this, ThirdActivity.class);
        startActivity(myThirdActivity);




    }



    public void viewExpensesByWeek(View view){

        int food=0;
        int travel=0;
        int shopping=0;
        int Misc=0;

        DbHelper db=DbHelper.getInstance(this);
        List<DBExpense> expensestable = db.getByWeek();
        Log.i("Size",String.valueOf(expensestable.size()));

       for(int i=0;i<expensestable.size();i++){

           food=food+expensestable.get(i).getFood();
           travel=travel+expensestable.get(i).getTravel();
           shopping=shopping+expensestable.get(i).getShopping();
           Misc=Misc+expensestable.get(i).getMisc();
       }


        Intent myPieCahrtActivity = new Intent(this, PieChartActivity.class);



        Bundle b = new Bundle();
        int total= food+travel+shopping+Misc;

        b.putString("Food",String.valueOf(food));
        b.putString("Travel",String.valueOf(travel));
        b.putString("Shopping",String.valueOf(shopping));
        b.putString("Misc",String.valueOf(Misc));
        b.putString("Total",String.valueOf(total));
        myPieCahrtActivity.putExtras(b);
        startActivity(myPieCahrtActivity);




    }

    public void viewExpensesByMonth(View view){

        int food=0;
        int travel=0;
        int shopping=0;
        int Misc=0;

        DbHelper db=DbHelper.getInstance(this);
        List<DBExpense> expensestable = db.getByMonth();
        Log.i("Size",String.valueOf(expensestable.size()));

        for(int i=0;i<expensestable.size();i++){

            food=food+expensestable.get(i).getFood();
            travel=travel+expensestable.get(i).getTravel();
            shopping=shopping+expensestable.get(i).getShopping();
            Misc=Misc+expensestable.get(i).getMisc();
        }


        Intent myPieCahrtActivity = new Intent(this, PieChartActivity.class);



        Bundle b = new Bundle();
        int total= food+travel+shopping+Misc;

        b.putString("Food",String.valueOf(food));
        b.putString("Travel",String.valueOf(travel));
        b.putString("Shopping",String.valueOf(shopping));
        b.putString("Misc",String.valueOf(Misc));
        b.putString("Total",String.valueOf(total));
        myPieCahrtActivity.putExtras(b);
        startActivity(myPieCahrtActivity);




    }
}
