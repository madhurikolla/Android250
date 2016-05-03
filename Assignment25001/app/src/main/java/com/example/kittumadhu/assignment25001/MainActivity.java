package com.example.kittumadhu.assignment25001;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onclickSubmit(View v){

                    final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                                    "\\@" +
                                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                    "(" +
                                    "\\." +
                                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                    ")+");
                    EditText email = (EditText)findViewById(R.id.email_address);


                 final String emailaddress= email.getText().toString();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                            if(!(EMAIL_ADDRESS_PATTERN.matcher(emailaddress).matches())){

                                Toast.makeText(getApplicationContext(), "Email is invalid",
                                        Toast.LENGTH_LONG).show();
                            }else {

                                Toast.makeText(getApplicationContext(), "Email is valid",
                                        Toast.LENGTH_LONG).show();
                            }



                }
}
