package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText did;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        myDb = new DatabaseHelper(this);
        did = (EditText) findViewById(R.id.deleteId);
        btnDelete = (Button) findViewById(R.id.delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(did.getText().toString())){
                    Toast.makeText(Delete.this, "ID Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                    deleteData();
            }
        });

    }

    public void deleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRow = myDb.deleteData(did.getText().toString());
                        if(deleteRow > 0)
                            Toast.makeText(Delete.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Delete.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}