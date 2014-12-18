package com.example.testapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDataBaseHelper extends SQLiteOpenHelper  {
	private static final String DATABASE_NAME = "todotable.db";
	  private static final int DATABASE_VERSION = 1;

	  public ToDoDataBaseHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		ToDoTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		ToDoTable.onUpgrade(db, oldVersion, newVersion);
	}

}
