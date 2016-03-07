package com.example.kittumadhu.spendingtracker01;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends ListActivity     {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.simple_layout);
/*
TextView textView = new TextView(getApplicationContext());
textView.setText("Select Category");
listView.addHeaderView(textView);
*/



        AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };


        AppCompatDelegate delegate = AppCompatDelegate.create(this, callback);

        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(SecondActivity.this);
            }
        });



//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.rowlayout, R.id.label, values);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                R.layout.activity_second,R.id.label,
//                getResources().getStringArray(R.array.categories_array));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories_array));

        getListView().setAdapter(adapter);



//        MySimpleArrayAdapter adapter01 = new MySimpleArrayAdapter(this, getResources().getStringArray(R.array.categories_array));
//        setListAdapter(adapter01);

    }

    @Override
    protected void onListItemClick(final ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

         // Get the item that was clicked
//           Log.d("position",String.valueOf(position));
//       String item = (String) getListAdapter().getItem(1);
//        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();

        l.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        Object o = l.getItemAtPosition(position);
                        String category = o.toString();
                        Toast.makeText(getApplicationContext(), "category chosen " + " " + category, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SecondActivity.this, CategoryActivity.class);
                        Bundle b=new Bundle();
                        b.putString("Category",category);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
        );
//
//            String item = (String) getListAdapter().getItem(position);
//             Log.d("category",item);




    }
}