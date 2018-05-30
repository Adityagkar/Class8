package com.acadview.assignment10_registration_filestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisplayActivity extends AppCompatActivity {


    TextView details;

    String fileText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        details=findViewById(R.id.textView7);

        try {  String FILE_NAME="mydata.txt";
            FileInputStream fileInputStream=openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            int charcount;
            char[] inputText=new char[100];
            fileText="";

            while((charcount=inputStreamReader.read(inputText))>0){
                fileText=fileText+String.copyValueOf(inputText,0,charcount);
            }
            inputStreamReader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        details.setText(""+fileText);

    }
}
