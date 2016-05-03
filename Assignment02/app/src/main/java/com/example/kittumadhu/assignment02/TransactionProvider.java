package com.example.kittumadhu.assignment02;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

    /**
 * Created by kittumadhu on 2/16/2016.
 */
public class TransactionProvider extends ContentProvider {

    private DbHelper database;
    private static final String AUTHORITY = "com.example.kittumadhu.assignment02.Transactions";

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int Transaction = 1;
    private static final int TransactionID = 2;

    static {
        sUriMatcher.addURI(AUTHORITY, "Transactions",Transaction);
        sUriMatcher.addURI(AUTHORITY, "Transactions/#", TransactionID);
    }

    @Override
    public boolean onCreate() {
        database= new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

              // Set the table
        queryBuilder.setTables(DbHelper.DB_TRANSACTION_TABLE);

        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
            case Transaction:
                break;
            case TransactionID:
                // adding the ID to the original query
                queryBuilder.appendWhere(DbHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


        @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case Transaction:
                id = sqlDB.insert(DbHelper.DB_TRANSACTION_TABLE, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(Transaction + "/" + id);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case Transaction:
                rowsDeleted = sqlDB.delete(DbHelper.DB_TRANSACTION_TABLE, selection,
                        selectionArgs);
                break;
            case TransactionID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(DbHelper.DB_TRANSACTION_TABLE, DbHelper.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(DbHelper.DB_TRANSACTION_TABLE,
                            DbHelper.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;

    }




    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case Transaction:
                rowsUpdated = sqlDB.update(DbHelper.DB_TRANSACTION_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case TransactionID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(DbHelper.DB_TRANSACTION_TABLE,
                            values,DbHelper.COLUMN_ID+ "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(DbHelper.DB_TRANSACTION_TABLE,
                            values,
                            DbHelper.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;

    }
}
