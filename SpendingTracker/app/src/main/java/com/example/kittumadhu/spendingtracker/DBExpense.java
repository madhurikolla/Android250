package com.example.kittumadhu.spendingtracker;

import java.util.Date;

/**
 * Created by kittumadhu on 12/6/2015.
 */
public class DBExpense {



    public int ExpenseId;


    public Date date;
    public int Food;
    public int Travel;
    public int Shopping;
    public int Misc;

    public DBExpense(Date date, int Food, int Travel, int Shopping, int Misc) {
        this.date=date;
        this.Food=Food;
        this.Travel=Travel;
        this.Shopping=Shopping;
        this.Misc=Misc;
    }


    public DBExpense( int Food, int Travel, int Shopping, int Misc) {
       // this.date=date;
        this.Food=Food;
        this.Travel=Travel;
        this.Shopping=Shopping;
        this.Misc=Misc;
    }

    public int getFood() {
        return Food;
    }

    public void setFood(int food) {
        Food = food;
    }



    public int getTravel() {
        return Travel;
    }

    public void setTravel(int travel) {
        Travel = travel;
    }



    public int getShopping() {
        return Shopping;
    }

    public void setShopping(int shopping) {
        Shopping = shopping;
    }



    public int getMisc() {
        return Misc;
    }

    public void setMisc(int misc) {
        Misc = misc;
    }



    public int getExpenseId() {
        return ExpenseId;
    }

    public void setExpenseId(int expenseId) {
        ExpenseId = expenseId;
    }


    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        date = date;
    }


}
