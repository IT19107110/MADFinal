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

public class Update extends AppCompatActivity {

    DatabaseHelper myDb;
    DatePickerDialog picker;
    EditText id, uDate;
    Spinner uDeparture, uArrival, uType, uAgency, uStatus;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.updateId);
        uDate=(EditText) findViewById(R.id.updateDate);
        uDeparture =(Spinner) findViewById(R.id.updateDeparture);
        uArrival =(Spinner) findViewById(R.id.updateArrival);
        uType = (Spinner) findViewById(R.id.updateType);
        uAgency = (Spinner) findViewById(R.id.updateAgency);
        uStatus = (Spinner) findViewById(R.id.updateStatus);
        btnUpdate = (Button) findViewById(R.id.update);

        uDate.setInputType(InputType.TYPE_NULL);
        uDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Update.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                uDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });




        Spinner spinner1 = (Spinner) findViewById(R.id.updateDeparture);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Update.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.departure));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.updateArrival);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Update.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arrival));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.updateType);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Update.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.updateAgency);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(Update.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.agency));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.updateStatus);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(Update.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.status));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(id.getText().toString())){
                    Toast.makeText(Update.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(uDate.getText().toString())){
                    Toast.makeText(Update.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                }
                else
                    UpdateData();
            }
        });

    }

    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(id.getText().toString(), uDate.getText().toString(), uDeparture.getSelectedItem().toString(), uArrival.getSelectedItem().toString(),
                                uType.getSelectedItem().toString(), uAgency.getSelectedItem().toString(), uStatus.getSelectedItem().toString());
                        if (isUpdated == true)
                            Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Update.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}