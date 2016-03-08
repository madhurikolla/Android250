package com.example.kittumadhu.spendingtracker01;

import android.app.Activity;
import android.app.ListActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionLogActivity extends  AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_log);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        AppCompatCallback callback = new AppCompatCallback() {
//            @Override
//            public void onSupportActionModeStarted(ActionMode actionMode) {
//            }
//
//            @Override
//            public void onSupportActionModeFinished(ActionMode actionMode) {
//            }
//
//            @Nullable
//            @Override
//            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
//                return null;
//            }
//        };
//
//
//        AppCompatDelegate delegate = AppCompatDelegate.create(this, callback);
//
//        delegate.onCreate(savedInstanceState);
//
//        delegate.setContentView(R.layout.activity_transaction_log);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        delegate.setSupportActionBar(toolbar);
//        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//
//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavUtils.navigateUpFromSameTask(TransactionLogActivity.this);
//            }
//        });

        Bundle extras=getIntent().getExtras();

        String [] Category = extras.getStringArray("Category");
        int [] amount=extras.getIntArray("Amount");
        String description=extras.getString("Description");

//        ArrayList<DBExpense> dbexpenses=new ArrayList<>();
//
//        for(int i=0;i<amount.length;i++) {
//            if(amount[i]!=0) {
//                DBExpense dbExpense = new DBExpense(Category[i], amount[i]);
//                dbexpenses.add(dbExpense);
//            }
//        }




        ArrayList<String> category01= new ArrayList<>();

        ArrayList<String> amount01= new ArrayList<>();
//
//
////        for(int i=0;i<Category.length;i++) {
////            category01.add(Category[i]);
////        }
//
        for(int i=0;i<amount.length;i++) {
            if(amount[i]!=0) {
                amount01.add(String.valueOf(amount[i]));
                category01.add(Category[i]);
            }
        }

        float total=0;
        for(int i=0;i<amount.length;i++) {

            total+=amount[i];
        }

//        category01.add("Total Spent");
//        amount01.add(String.valueOf(total));


////
        TextView tv =(TextView)findViewById(R.id.label);

        tv.setText(description);

        TextView tv01 =(TextView)findViewById(R.id.label2);

        tv01.setText("Total Spent: $"+String.valueOf(total));

//        for(int i=0;i<category01.size();i++) {
//            tv.setText(category01.get(i));
//        }
//
//        for(int i=0;i<amount01.size();i++) {
//            tv01.setText(amount01.get(i));
//        }

        ListView listView = (ListView)findViewById(R.id.listViewTasks);
        ListView listView1 = (ListView)findViewById(R.id.listViewTasks01);


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    category01);
        ArrayAdapter<String> arrayAdapter01 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                amount01);


            listView1.setAdapter(arrayAdapter);
            listView.setAdapter(arrayAdapter01);

      //  listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);



//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,category01);

//        ArrayList adapter01= new ArrayList<DBExpense>(this,
//                android.R.layout.simple_list_item_1,dbexpenses);

//        ArrayAdapter<String> adapter01 = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,category01);
      //  getListView().setAdapter(adapter);

//        ArrayAdapter<String> adapter01 = new ArrayAdapter<>(this,
//                R.layout.activity_transaction_log,R.id.label,
//                category01);

          // setListAdapter(adapter);
       // setListAdapter(adapter01);
        //getListView().setAdapter(adapter);
       // getListView().setAdapter(adapter01);
    }
}
