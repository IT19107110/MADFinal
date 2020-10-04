package com.example.schedule;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    DatePickerDialog picker;
    EditText eDate;
    Spinner eDeparture, eArrival, eType, eAgency, eStatus;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        eDate=(EditText) findViewById(R.id.editDate);
        eDeparture =(Spinner) findViewById(R.id.editDeparture);
        eArrival =(Spinner) findViewById(R.id.editArrival);
        eType = (Spinner) findViewById(R.id.editType);
        eAgency = (Spinner) findViewById(R.id.editAgency);
        eStatus = (Spinner) findViewById(R.id.editStatus);
        btnAdd = (Button) findViewById(R.id.add);


        eDate.setInputType(InputType.TYPE_NULL);
        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.editDeparture);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.departure));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.editArrival);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arrival));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.editType);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.editAgency);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.agency));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.editStatus);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.status));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eDate.getText().toString())){
                    Toast.makeText(MainActivity.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                }
                else
                    addSchedule();
            }
        });





    }

    public void addSchedule() {

        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                       boolean isInserted = myDb.insertData(eDate.getText().toString(), eDeparture.getSelectedItem().toString(), eArrival.getSelectedItem().toString(),
                                eType.getSelectedItem().toString(), eAgency.getSelectedItem().toString(), eStatus.getSelectedItem().toString());

                       if(isInserted == true)
                           Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }





}




