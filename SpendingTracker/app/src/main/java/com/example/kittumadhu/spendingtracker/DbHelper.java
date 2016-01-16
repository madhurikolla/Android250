package com.example.kittumadhu.spendingtracker;

/**
 * Created by kittumadhu on 12/6/2015.
 */


        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;
        import java.util.Locale;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db05";
    private static final int DB_VERSION = 1;
    private static DbHelper myDb = null;
    private SQLiteDatabase db;
    public static final String DB_EXPENSES_TABLE = "Expenses";
    private static final String CreateExpensesTable = "create table "
            +  DB_EXPENSES_TABLE
            + "(ID integer primary key autoincrement, Date date, Food integer, Travel integer" +
            ",Shopping integer, Misc integer, Total integer )" ;

    private DbHelper(Context context){
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
        db.execSQL(CreateExpensesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_EXPENSES_TABLE);
        onCreate(db);
    }

    private void open() throws SQLiteException
    {
        db = getWritableDatabase();
    }

    public long addSession( Date date, int food, int travel,int shopping,int misc )
    {

        int total =food+travel+shopping+misc;
        ContentValues values = new ContentValues();

//        values.put("Date", date.toString());
        values.put("Date",date.getTime());
        values.put("Food",food);
        values.put("Travel",travel);
        values.put("Shopping",shopping);
        values.put("Misc",misc);
        values.put("Total", total);


        long rowId = db.insert(DB_EXPENSES_TABLE, null, values);
        return rowId;
    }

    public ArrayList<DBExpense> getSessions()
    {
        ArrayList<DBExpense> expenses = new ArrayList<DBExpense>();

        Cursor cursor = db.query(DB_EXPENSES_TABLE, null, null, null, null,
                null, null);


        if (cursor.moveToFirst()) {
            while (true)


            {

                Log.i("call info",String.valueOf(new Date(cursor.getLong(cursor.getColumnIndex("Date")))));
                DBExpense expense = new DBExpense(new Date(cursor.getLong(cursor.getColumnIndex("Date"))),
                        cursor.getInt(cursor.getColumnIndex("Food")),cursor.getInt(cursor.getColumnIndex("Travel")),
                        cursor.getInt(cursor.getColumnIndex("Shopping")),cursor.getInt(cursor.getColumnIndex("Misc")));




               expenses.add(expense);

                if (!cursor.moveToNext())
                {
                    break;
                }
            }
        }

        cursor.close();

//        DBExpense[] dbExpenses = new DBExpense[expenses.size()];
//
//        expenses.toArray(dbExpenses);

        return expenses;
    }

    public void updateSession(long id, Date date, int food, int travel,int shopping,int misc )
    {
        ContentValues values = new ContentValues();
        int total =food+travel+shopping+misc;
        values.put("Date", date.getTime());
        values.put("Food",food);
        values.put("Travel",travel);
        values.put("Shopping", shopping);
        values.put("Misc",misc);
        values.put("Total",total);
        db.update(DB_EXPENSES_TABLE, values, "ID=" + id, null);
    }

    public List<DBExpense>  getByDate(Date date)
    {

        ArrayList<DBExpense> expenses = new ArrayList<DBExpense>();

        try{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


        String string01= "Date = "+"'"+date.getTime()+"'";
        //Log.i("date", date.toString());

//        Cursor cursor = db.query(DB_EXPENSES_TABLE, null, null, null, null,
//                null, null);
        Cursor cursor= db.query(DB_EXPENSES_TABLE, new String[]  {"Food","Travel","Shopping","Misc"},
                string01,null,null,null,null);


        if (cursor.moveToFirst()) {
            while (true)
            {
                DBExpense expense = new DBExpense(cursor.getInt(cursor.getColumnIndex("Food")),
                        cursor.getInt(cursor.getColumnIndex("Travel")),
                        cursor.getInt(cursor.getColumnIndex("Shopping")),cursor.getInt(cursor.getColumnIndex("Misc")));



                expenses.add(expense);

                if (!cursor.moveToNext())
                {
                    break;
                }
            }
        }

        cursor.close();}catch(Exception ex){

            Log.i("Parse Exception",ex.getMessage());
            }

//        DBExpense[] dbExpenses = new DBExpense[expenses.size()];
//
//        expenses.toArray(dbExpenses);

        return expenses;


    }


    public List<DBExpense>  getByWeek()
    {

        ArrayList<DBExpense> expenses = new ArrayList<DBExpense>();

        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Calendar c= Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = (c.get(Calendar.MONTH))+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int patday=day-7;
            String currDate=day+"/"+month+"/"+year;
            String preDate=patday+"/"+month+"/"+year;
            Date currentDate=df.parse(currDate);
            Log.i("date",currentDate.toString());
            Date pastDate=df.parse(preDate);
            Log.i("date",pastDate.toString());

            String string01= "Date >= "+"'"+pastDate.getTime()+"'"+" and Date <= "+"'"+currentDate.getTime()+"'";
            //Log.i("date", date.toString());
//            String string02= "Date >= "+"'"+pastDate+"'";

//        Cursor cursor = db.query(DB_EXPENSES_TABLE, null, null, null, null,
//                null, null);
            Cursor cursor= db.query(DB_EXPENSES_TABLE, new String[]  {"Date","Food","Travel","Shopping","Misc"},
                    string01,null,null,null,null);



            if (cursor.moveToFirst()) {
                while (true)
                {
//                    Log.i("date-info:",df.format((cursor.getColumnIndex("Date"));
//                    Date dategg=new Date(cursor.getLong(cursor.getColumnIndex("Date")));
//                   Log.i("Output-date", String.valueOf(cursor.getLong(cursor.getColumnIndex("Date"))));
//                    String dateX =dategg.toString();
//                    Log.i("date -info",df.parse(dateX).toString());

                    DBExpense expense = new DBExpense(new Date(cursor.getLong(cursor.getColumnIndex("Date"))),
                            cursor.getInt(cursor.getColumnIndex("Food")),cursor.getInt(cursor.getColumnIndex("Travel")),
                            cursor.getInt(cursor.getColumnIndex("Shopping")),cursor.getInt(cursor.getColumnIndex("Misc")));



                    expenses.add(expense);

                    if (!cursor.moveToNext())
                    {
                        break;
                    }
                }
            }

            cursor.close();

            }catch(Exception ex){

            Log.i("Parse Exception",ex.getMessage());
        }

//        DBExpense[] dbExpenses = new DBExpense[expenses.size()];
//
//        expenses.toArray(dbExpenses);

        return expenses;


    }



    public List<DBExpense>  getByMonth()
    {

        ArrayList<DBExpense> expenses = new ArrayList<DBExpense>();

        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Calendar c= Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = (c.get(Calendar.MONTH))+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int patday=day - 30;;
//            if(day==31) {
//                patday = day - 31;
//            }else {
//                patday = day - 30;
//            }
            String currDate=day+"/"+month+"/"+year;
            String preDate=patday+"/"+month+"/"+year;
            Date currentDate=df.parse(currDate);
            Log.i("date",currentDate.toString());
            Date pastDate=df.parse(preDate);
            Log.i("date",pastDate.toString());

            String string01= "Date >= "+"'"+pastDate.getTime()+"'"+" and Date <= "+"'"+currentDate.getTime()+"'";
            //Log.i("date", date.toString());
//            String string02= "Date >= "+"'"+pastDate+"'";

//        Cursor cursor = db.query(DB_EXPENSES_TABLE, null, null, null, null,
//                null, null);
            Cursor cursor= db.query(DB_EXPENSES_TABLE, new String[]  {"Date","Food","Travel","Shopping","Misc"},
                    string01,null,null,null,null);



            if (cursor.moveToFirst()) {
                while (true)
                {
//                    Log.i("date-info:",df.format((cursor.getColumnIndex("Date"));
//                    Date dategg=new Date(cursor.getLong(cursor.getColumnIndex("Date")));
//                   Log.i("Output-date", String.valueOf(cursor.getLong(cursor.getColumnIndex("Date"))));
//                    String dateX =dategg.toString();
//                    Log.i("date -info",df.parse(dateX).toString());

                    DBExpense expense = new DBExpense(new Date(cursor.getLong(cursor.getColumnIndex("Date"))),
                            cursor.getInt(cursor.getColumnIndex("Food")),cursor.getInt(cursor.getColumnIndex("Travel")),
                            cursor.getInt(cursor.getColumnIndex("Shopping")),cursor.getInt(cursor.getColumnIndex("Misc")));



                    expenses.add(expense);

                    if (!cursor.moveToNext())
                    {
                        break;
                    }
                }
            }

            cursor.close();

        }catch(Exception ex){

            Log.i("Parse Exception",ex.getMessage());
        }

//        DBExpense[] dbExpenses = new DBExpense[expenses.size()];
//
//        expenses.toArray(dbExpenses);

        return expenses;


    }


    public void deleteSession(long id)
    {
        db.delete(DB_EXPENSES_TABLE, "ID=" + id, null);
    }

    public void deleteSessions()
    {
        db.delete(DB_EXPENSES_TABLE, null, null);
    }
}