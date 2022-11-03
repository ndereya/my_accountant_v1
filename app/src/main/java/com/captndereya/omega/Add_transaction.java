package com.captndereya.omega;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Add_transaction extends AppCompatActivity {
    private EditText Et_ammount, Et_date, Et_details, Et_type;
    DatePickerDialog datePickerDialog;
    private Button saveRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        saveRecord = (Button) findViewById(R.id.add_record);
        Et_ammount = (EditText) findViewById(R.id.et_ammount);
        Et_date = (EditText) findViewById(R.id.et_date);
        Et_details =(EditText) findViewById(R.id.et_description);
        Et_type = (EditText) findViewById(R.id.et_type);


        Et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callender instance
                final Calendar calender = Calendar.getInstance();
                int mYear  = calender.get(Calendar.YEAR);//current year
                int mMonth =  calender.get(Calendar.MONTH);//current month
                int mDate = calender.get(Calendar.DAY_OF_MONTH);//current date

                //datepicker dialog
                datePickerDialog = new DatePickerDialog(Add_transaction.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day_of_month) {
                        //lets set the day of the month, month and year
                        Et_date.setText(day_of_month+"/"+ (month +1)+"/"+ year);
                    }
                },mYear,mMonth,mDate);
                datePickerDialog.show();
            }
        });

        saveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper =  new DbHelper(Add_transaction.this);
                String date = Et_date.getText().toString().trim();
                String details = Et_details.getText().toString().trim();
                String type = Et_type.getText().toString().trim();
                String amount  = Et_ammount.getText().toString().trim();
                dbHelper.addRecord(amount,type,details,date);
            }
        });

    }
}