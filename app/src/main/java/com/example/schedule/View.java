package com.example.schedule;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class View extends AppCompatActivity {

    DatabaseHelper Db;
    Button btnViewAll, btnViewId;
    Spinner pDeparture, pArrival, pType, pAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Db = new DatabaseHelper(this);
        pDeparture =(Spinner) findViewById(R.id.pickDeparture);
        pArrival =(Spinner) findViewById(R.id.pickArrival);
        pType = (Spinner) findViewById(R.id.pickType);
        pAgency = (Spinner) findViewById(R.id.pickAgency);
        btnViewId = (Button) findViewById(R.id.viewId);
        btnViewAll = (Button) findViewById(R.id.viewAll);

        Spinner spinner1 = (Spinner) findViewById(R.id.pickDeparture);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(View.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.departure));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.pickArrival);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(View.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arrival));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.pickType);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(View.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.pickAgency);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(View.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.agency));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        viewAll();
        viewId();


    }



    public void viewAll(){
        btnViewAll.setOnClickListener(
                new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        Cursor res = Db.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","No Data Found");
                            return;
                        }

                        int i=0;
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :" + res.getString(0)+"\n");
                            buffer.append("Date :" + res.getString(1)+"\n");
                            buffer.append("Departure :" + res.getString(2)+"\n");
                            buffer.append("Arrival :" + res.getString(3)+"\n");
                            buffer.append("Type :" + res.getString(4)+"\n");
                            buffer.append("Agency :" + res.getString(5)+"\n");
                            buffer.append("Status :" + res.getString(6)+"\n\n");
                            i++;
                        }

                        showMessage(i + " Result(s) Found", buffer.toString());


                    }
                }
        );
    }

    public void viewId(){
        btnViewId.setOnClickListener(
                new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        Cursor res = Db.getId(pDeparture.getSelectedItem().toString(), pArrival.getSelectedItem().toString(),
                                pType.getSelectedItem().toString(), pAgency.getSelectedItem().toString());
                        if(res.getCount() == 0){
                            showMessage("Error","No ID Found");
                            return;
                        }

                        int i=0;
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            i++;
                            buffer.append("Id :" + res.getString(0)+"\n");


                        }

                        showMessage(i + " Result(s) Found" , buffer.toString());


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