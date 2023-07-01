package com.my;
import android.database.sqlite.*;
import android.content.*;
import java.util.*;
import android.database.*;
import java.io.*;

public class SQLiteDBHelper extends SQLiteOpenHelper
{
	Context c;
	public static final String TABLE_NAME="AudioRecord";
	public static final String DB_NAME="AudioRecorder.db";
	public static final String ID="id";
	public static final String FILEABSNAME="fileAbsName";
	public SQLiteDBHelper(Context c)
	{
		super(c,DB_NAME, null, 5);
	}
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		String query = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FILEABSNAME+" TEXT"+")";
		p1.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
	}
	public void addNewRecord(String fileAbsName)
	{
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
		
        values.put(FILEABSNAME, fileAbsName);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
	public ArrayList<Record> readRecords()
	{
		ArrayList<Record> recordArrayList
			= new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor
			= db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
		
		if (cursor.moveToFirst())
		{
			do {
				recordArrayList.add(new Record(cursor.getString(1)));
			} while (cursor.moveToNext());

		}
		cursor.close();
		return recordArrayList;
	}
	public void deleteRecord(String fan){
		SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, FILEABSNAME+"=?", new String[]{fan});
        db.close();
	}
}
