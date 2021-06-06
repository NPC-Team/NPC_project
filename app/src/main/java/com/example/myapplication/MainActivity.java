package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(2000); //wait per millis
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, Map_activity.class));
        finish();

        readCsvDate();
    }

    private List<CsvSampele> CsvSample= new ArrayList<>();
    private void readCsvDate() {
        InputStream is = getResources() . openRawResource(R.raw.npccsv);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: "+ line);
                String [] tokens = line.split(",");
                CsvSampele sample = new CsvSampele();
                sample.setNumber(Integer.parseInt(tokens[0]));
                sample.setName((tokens[1]));
                sample.setLatitude(Double.parseDouble(tokens[2]));
                sample.setLongitude(Double.parseDouble(tokens[3]));
                CsvSample.add(sample);

                Log.d("MyActivity", "Just created: " + sample);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading NpcCsv file on line" + line, e);
            e.printStackTrace();
        }
    }
}