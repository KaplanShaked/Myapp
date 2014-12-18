package com.example.testapp;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.database.*;
import android.database.sqlite.*;

public class MyDodoContentProvider extends ContentProvider {
	private ToDoDataBaseHelper db;
	private static final int TODOS = 10;
	private static final int TODO_ID = 20;
	 private static final String AUTHORITY = "com.example.testapp.contentprovider";
	 private static final String BASE_PATH = "todos";
	  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	      + "/" + BASE_PATH);

	  public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	      + "/todos";
	  public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	      + "/todo";

	  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
	  }


	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		db=new ToDoDataBaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		checkColumns(projection);
		return null;
	}

	private void checkColumns(String[] projection) {
		// TODO Auto-generated method stub
	    String[] available = { ToDoTable.COLUMN_CATEGORY,
	            ToDoTable.COLUMN_SUMMARY, ToDoTable.COLUMN_DESCRIPTION,
	            ToDoTable.COLUMN_ID };
	        if (projection != null) {
	          HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
	          HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
	          // check if all columns which are requested are available
	          if (!availableColumns.containsAll(requestedColumns)) 
	            throw new IllegalArgumentException("Unknown columns in projection");
	        }
	}
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		  int uriType = sURIMatcher.match(uri);
		    SQLiteDatabase sqlDB = db.getWritableDatabase();
		    int rowsDeleted = 0;
		    long id = 0;
		    switch (uriType) {
		    case TODOS:
		      id = sqlDB.insert(ToDoTable.TABLE_TODO, null, values);
		      break;
		    default:
		      throw new IllegalArgumentException("Unknown URI: " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		  int uriType = sURIMatcher.match(uri);
		    SQLiteDatabase sqlDB = db.getWritableDatabase();
		    int rowsDeleted = 0;
		    switch (uriType) {
		    case TODOS:
		      rowsDeleted = sqlDB.delete(ToDoTable.TABLE_TODO, selection,
		          selectionArgs);
		      break;
		    case TODO_ID:
		      String id = uri.getLastPathSegment();
		      if (TextUtils.isEmpty(selection)) {
		        rowsDeleted = sqlDB.delete(ToDoTable.TABLE_TODO,
		            ToDoTable.COLUMN_ID + "=" + id, 
		            null);
		      } else {
		        rowsDeleted = sqlDB.delete(ToDoTable.TABLE_TODO,
		            ToDoTable.COLUMN_ID + "=" + id 
		            + " and " + selection,
		            selectionArgs);
		      }
		      break;
		    default:
		      throw new IllegalArgumentException("Unknown URI: " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return rowsDeleted;	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = db.getWritableDatabase();
	    int rowsUpdated = 0;
	    switch (uriType) {
	    case TODOS:
	      rowsUpdated = sqlDB.update(ToDoTable.TABLE_TODO, 
	          values, 
	          selection,
	          selectionArgs);
	      break;
	    case TODO_ID:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsUpdated = sqlDB.update(ToDoTable.TABLE_TODO, 
	            values,
	            ToDoTable.COLUMN_ID + "=" + id, 
	            null);
	      } else {
	        rowsUpdated = sqlDB.update(ToDoTable.TABLE_TODO, 
	            values,
	            ToDoTable.COLUMN_ID + "=" + id 
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
