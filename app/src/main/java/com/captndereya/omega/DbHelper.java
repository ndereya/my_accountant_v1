package com.captndereya.omega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "TheAccountant.db";
    private static final int DATABASE_VERSION = 1;
    //    table 1
    private static final String TABLE_NAME = "Transactions";
    private static final String TRANSACTION_ID ="t_id";
    private static final String  TRANSACTION_AMOUNT="t_ammount";
    private static  final String  TRANSACTION_TYPE="t_type";
    private static final String  TRANSACTION_DETAILS="t_details";
    private static final String  TRANSACTION_DATE="t_date";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query = "CREATE TABLE " + TABLE_NAME + "(" +
                    TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TRANSACTION_AMOUNT + "TEXT, "
                    + TRANSACTION_TYPE + "TEXT, "
                    + TRANSACTION_DETAILS + "TEXT, "
                    + TRANSACTION_DATE + "DATE );";
            db.execSQL(query);
        }catch (Exception e){

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

    void addRecord(String ammount, String type, String desc, String date ){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        contentValues.put(TRANSACTION_AMOUNT,ammount);
        contentValues.put(TRANSACTION_TYPE,type);
        contentValues.put(TRANSACTION_DETAILS,desc);
        contentValues.put(TRANSACTION_DATE, date);

        long result =  db.insert(TABLE_NAME,null,contentValues);
        if(result == 0){
            Toast.makeText(context, "Failed to add a record", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added", Toast.LENGTH_LONG).show();
        }
    }

    //    reading data
    Cursor readRecord(){

        String query = "SELECT * FROM " + TABLE_NAME+" ORDER BY+ "+ TRANSACTION_ID +" DESC";
        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor =null;
        if(db != null){
            cursor =   db.rawQuery(query,null);

        }
        return cursor;
    }
    public String readIncomes(){

            String sAmount;
           // String query = "SELECT  SUM("+(DbHelper.TRANSACTION_AMOUNT)+") FROM Transactions WHERE "+DbHelper.TRANSACTION_TYPE+" ='income'";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT  (SUM("+(DbHelper.TRANSACTION_AMOUNT)+")) FROM Transactions WHERE "+DbHelper.TRANSACTION_TYPE+" ='income'", null);
            if (cursor.moveToFirst()) {
                sAmount = String.valueOf(cursor.getInt(0));

            } else {
                sAmount = "0";
            }
        cursor.close();
        db.close();
            return sAmount;
    }
    public String readExpenses(){
        String sAmount;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT SUM("+(TRANSACTION_AMOUNT)+") FROM Transactions WHERE t_type ='expense'",null);
        if (cursor.moveToFirst()){
            sAmount = String.valueOf(cursor.getInt(0));

        }else {
            sAmount = "0";
        }
        cursor.close();
        db.close();
        return sAmount;
    }

}
