package com.example.kittumadhu.assignment02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db10";
    private static final int DB_VERSION = 1;
    private static DbHelper myDb = null;
    private SQLiteDatabase db;
    public static final String DB_TRANSACTION_TABLE = "Transactions";
    public static final String COLUMN_ID = "TransactionID";
    private static final String CreateTransactionTable = "create table "
            +  DB_TRANSACTION_TABLE
            + "( " + COLUMN_ID + "integer primary key autoincrement, Date date, Description text, Amount integer )";

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        open();
    }

    public static DbHelper getInstance(Context context)
    {
        if (myDb == null)
        {
            myDb = new DbHelper(context);
        }

        return myDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTransactionTable );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TRANSACTION_TABLE);
        onCreate(db);
    }

    private void open() throws SQLiteException
    {
        db = getWritableDatabase();
    }

    public long addTransaction(Date date, String desc, int amount)
    {
        ContentValues values = new ContentValues();
        values.put("Date", date.getTime());
        Log.i("date by time", String.valueOf(date.getTime()));
        values.put("Description",desc);
        values.put("Amount",amount);
        long rowId = db.insert(DB_TRANSACTION_TABLE, null, values);
        return rowId;
    }

    public List<DbTransaction> getTransactions()
    {
        ArrayList<DbTransaction> transactions = new ArrayList<DbTransaction>();

        Cursor cursor = db.query(DB_TRANSACTION_TABLE, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            while (true)
            {
                DbTransaction transaction= new DbTransaction();

                transaction.transactionId = cursor.getInt(cursor.getColumnIndex("TransactionID"));



                transaction.date = new Date(cursor.getLong(cursor.getColumnIndex("Date")));
                transaction.description = cursor.getString(cursor.getColumnIndex("Description"));
                transaction.amount=cursor.getInt(cursor.getColumnIndex("Amount"));


                transactions.add(transaction);
                if (!cursor.moveToNext())
                {
                    break;
                }
            }
        }

        cursor.close();

        return transactions;
    }

    public void updateTransaction(long id,Date date,String desc, int amount)
    {
        ContentValues values = new ContentValues();
        values.put("Date", date.getTime());
        Log.i("date by time", String.valueOf(date.getTime()));
        values.put("Description",desc);
        values.put("Amount",amount);

       db.update(DB_TRANSACTION_TABLE, values, "TransactionID=" + id, null);
    }

    public void deleteTransaction(long id)
    {
        db.delete(DB_TRANSACTION_TABLE, "TransactionID=" + id, null);
    }

    public void deleteTransaction()
    {
        db.delete(DB_TRANSACTION_TABLE, null, null);
    }
}
