package com.acadview.assignment10_registration_filestorage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,phone,city;
    Button submit;
    Spinner state;
    String state_string;
    Integer state_position;
    String fileText;

    String FILE_NAME="mydata.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.phone);
        city = findViewById(R.id.city);
        state = findViewById(R.id.statespinner);
        submit = findViewById(R.id.submit);




        final String states[] = {"Bihar", "Chhattisgarh", "Jharkhand", "Madhya Pradesh", "Odisha", "Rajasthan", "Uttar Pradesh"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, states);
        state.setAdapter(arrayAdapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state_string = states[i];
                state_position=i;
                fileText=" Name : "+name.getText()+" Email : "+mail.getText()+" Phone: "+phone.getText()+" State : "+states[i]+" City : "+city.getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "You haven't selected anything ! ", Toast.LENGTH_LONG).show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             confirmAlert();
            }
        });


    }

    private void confirmAlert(){
        AlertDialog.Builder alertdialog= new AlertDialog.Builder(MainActivity.this);

        alertdialog.setTitle("Confirm !");
        alertdialog.setMessage("Are you sure you want to save data values ?");

        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    int charCount;
                    outputStreamWriter.write(fileText);
                    Toast.makeText(getApplicationContext(),"All details are stored in File ",Toast.LENGTH_LONG).show();

                    startActivity(intent);
                    outputStreamWriter.close();

                }
                catch(Exception e){
                    e.printStackTrace();
                }



            }
        });

        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog alertDialogBox=alertdialog.create();
        alertDialogBox.show();
    }


}