package com.captndereya.omega;

import static java.lang.String.format;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    DbHelper dbHelper;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ArrayList<String> date,amount,type,notes;
    TextView totalIncome,TotalExpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalIncome = findViewById(R.id.incomes);
        TotalExpenses = findViewById(R.id.expenses);
        dbHelper =  new DbHelper(this);
       totalIncome.setText(dbHelper.readIncomes());
       TotalExpenses.setText(format("%s",dbHelper.readExpenses()));


        recyclerView =  findViewById(R.id.recyclerView);
        floatingActionButton  =  findViewById(R.id.add_transaction);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Add_transaction.class);
            startActivity(intent);
        });
//     magic for recycler view
        dbHelper = new DbHelper(this);
        date = new ArrayList<>();
        amount = new ArrayList<>();
        type = new ArrayList<>();
        notes = new ArrayList<>();

//        change to income icon if income else expense icon


        customAdapter = new CustomAdapter(this,date,amount,type,notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    private void displayData() {
        Cursor cursor = dbHelper.readRecord();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                date.add(cursor.getString(2));
                amount.add(cursor.getString(4));
                type.add(cursor.getString(1));
                notes.add(cursor.getString(3));
            }

        }

    }

}