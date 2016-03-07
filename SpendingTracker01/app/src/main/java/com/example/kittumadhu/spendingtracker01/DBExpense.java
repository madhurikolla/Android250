package com.example.kittumadhu.spendingtracker01;

/**
 * Created by kittumadhu on 2/27/2016.
 */



import java.util.Date;

/**
 * Created by kittumadhu on 12/6/2015.
 */
public class DBExpense {



    public int ExpenseId;
    public Date date;
    public String category;
    public int amount;


    public DBExpense(Date date, String category, int amount) {
        this.date=date;
        this.category=category;
        this.amount=amount;

    }


    public DBExpense( String category, int amount) {

        this.category=category;
        this.amount=amount;

    }


//    public DBExpense( int Food, int Travel, int Shopping, int Misc) {
//        // this.date=date;
//        this.Food=Food;
//        this.Travel=Travel;
//        this.Shopping=Shopping;
//        this.Misc=Misc;
//    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
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
