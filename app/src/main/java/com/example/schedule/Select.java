package com.example.schedule;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Select extends AppCompatActivity {

    DatabaseHelper Db;
    DatePickerDialog picker;
    EditText sDate;
    Button btnViewAgency;
    Spinner sDeparture, sArrival, sType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Db = new DatabaseHelper(this);
        sDate=(EditText) findViewById(R.id.selectDate);
        sDeparture =(Spinner) findViewById(R.id.selectDeparture);
        sArrival =(Spinner) findViewById(R.id.selectArrival);
        sType = (Spinner) findViewById(R.id.selectType);
        btnViewAgency = (Button) findViewById(R.id.viewAgency);

        sDate.setInputType(InputType.TYPE_NULL);
        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Select.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                sDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.selectDeparture);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Select.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.departure));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.selectArrival);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Select.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arrival));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.selectType);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Select.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        btnViewAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(sDate.getText().toString())){
                    Toast.makeText(Select.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                }
                else
                    viewAgency();
            }
        });




    }

    public void viewAgency(){
        btnViewAgency.setOnClickListener(
                new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        Cursor res = Db.getAgency(sDate.getText().toString(), sDeparture.getSelectedItem().toString(), sArrival.getSelectedItem().toString(),
                                sType.getSelectedItem().toString());
                        if(res.getCount() == 0){
                            showMessage("Error","No Buses Available");
                            return;
                        }

                        int i=0;
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){

                            i++;
                            buffer.append( i + "." + res.getString(0)+"\n");


                        }

                        showMessage(i + " Bus(es) Available" , buffer.toString());


                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}