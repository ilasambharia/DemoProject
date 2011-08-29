package com.diaspark.gif;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseDemo extends Activity {
	SQLiteDatabase db;
	TextView txtMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		setContentView(R.layout.dbaselayout);
		txtMsg= (TextView) findViewById(R.id.txtMsg);
		try{
			openRDatabase();
		
			/*openDatabase();//open (create if needed) database
			dropTable();//if needed drop table tblAmigos
			insertSomeDbData();//create-populate tblAmigos
			useRawQuery1(); //fixed SQL with no arguments
			useRawQuery2(); //parameter substitution
			
			useSimpleQuery1();//simple query
		
			useCursor1();//retrieve rows from a table
			
			useInsertMethod();//use insert method
			useUpdateMethod();//use update method
			useDeleteMethod();//use delete method*/
			//db.close();//make sure to release the DB
			Toast.makeText(this,"Alldone changes!",1).show();
			} catch(Exception e) {
			Toast.makeText(this,e.getMessage(),1).show();
			}
		super.onCreate(savedInstanceState);

	}
	
	private void openDatabase() {
		try{
		db= SQLiteDatabase.openDatabase(
				"sdcard/myfriendsDB",
		null,
		SQLiteDatabase.CREATE_IF_NECESSARY) ;
		Toast.makeText(this, "DB was opened!", 1).show();
		}
		catch(SQLiteException e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		}//createDatabase
	
	private void insertSomeDbData() {
		//create table: tblAmigo
		db.beginTransaction();
		try{
		db.execSQL("create table tblAMIGO("
		+ " recID integer PRIMARY KEY autoincrement, "
		+ " name text, "
		+ " phone text ); ");
		//commit your changes
		db.setTransactionSuccessful();
		Toast.makeText(this, "Table was created",1).show();
		} catch(SQLException e1) {
		Toast.makeText(this, e1.getMessage(),1).show();
		}
		finally{
		//finish transaction processing
		db.endTransaction();
		}
		// populate table: tblAmigo
		db.beginTransaction();
		try{
		//insert rows
		db.execSQL( "insert into tblAMIGO(name, phone) "
		+ " values ('AAA', '555' );");
		db.execSQL("insert into tblAMIGO(name, phone) "
		+ " values ('BBB', '777' );");
		db.execSQL("insert into tblAMIGO(name, phone) "
		+ " values ('CCC', '999' );");
		//commit your changes
		db.setTransactionSuccessful();
		Toast.makeText(this, " 3 records were inserted",1).show();
		}
		catch(SQLiteException e2) {
		//report problem
		Toast.makeText(this, e2.getMessage(),1).show();
		}
		finally{
		db.endTransaction();
		}
		}//insertSomeData

	private void useRawQuery1() {
		try{
		//hard-coded SQL-select command with no arguments
		String mySQL="select count(*) as Total from tblAMIGO";
		Cursor c1 = db.rawQuery(mySQL, null);
		int index = c1.getColumnIndex("Total");
		//advance to the next record (first rec. if necessary)
		c1.moveToNext();
		int theTotal= c1.getInt(index);
		Toast.makeText(this, "Total1: "+ theTotal, 1).show();
		} catch(Exception e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		}//useRawQuery1
	
	private void useRawQuery2() {
		try{
		// ? arguments provided for automatic replacement
		String mySQL= " select count(*) as Total "
		+ " from tblAmigo"
		+ " where recID> ? "
		+ " and name = ? ";
		String[] args= {"1", "BBB"};
		Cursor c1 = db.rawQuery(mySQL, args);
		int index = c1.getColumnIndex("Total");
		//advance to the next record (first rec. if necessary)
		c1.moveToNext();
		int theTotal= c1.getInt(index);
		Toast.makeText(this, "Total2: "+ theTotal, 1).show();
		} catch(Exception e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		}//useRawQuery2
	
	private void useSimpleQuery1() {
		try{
		//simple (implicit) query on one table
		String [] columns = {"recID", "name", "phone"};
		Cursor c1 = db.query(
		"tblAMIGO",
		columns,
		"recID> 2 and length(name) >= 3 and name like 'B%' ",
		null, null, null,
		"recID");
		int theTotal= c1.getCount();
		Toast.makeText(this, "Total4: "+ theTotal, 1).show();
		} catch(Exception e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		}//useSimpleQuery1
	
	private void useCursor1() {
		try{
		txtMsg.append("\n");
		// obtain a list of <recId, name, phone> from DB
		String[] columns = { "recID", "name", "phone"};
		Cursor c = db.query("tblAMIGO", columns,
		null, null, null, null, "recID");
		int theTotal= c.getCount();
		Toast.makeText(this, "Total6: "+ theTotal, 1).show();
		int idCol= c.getColumnIndex("recID");
		int nameCol= c.getColumnIndex("name");
		int phoneCol= c.getColumnIndex("phone");
		while(c.moveToNext()) {
		columns[0] = Integer.toString((c.getInt(idCol)));
		columns[1] = c.getString(nameCol);
		columns[2] = c.getString(phoneCol);
		txtMsg.append( columns[0] + " "+ columns[1] + " "
		+ columns[2] + "\n");
		}
		} catch(Exception e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		}//useCursor1
	
	private void dropTable(){
		//(clean start) action query to drop table
		try{
		db.execSQL( " drop table tblAmigo; ");
		Toast.makeText(this, "Table dropped", 1).show();
		} catch(Exception e) {
		Toast.makeText(this,
		"dropTable()\n"+ e.getMessage(), 1).show();
		}
		}// dropTable
	
	public void useInsertMethod() {
		ContentValues initialValues= new ContentValues();
		initialValues.put("name", "ABC");
		initialValues.put("phone", "101");
		int rowPosition= (int) db.insert("tblAMIGO", null, initialValues);
		txtMsg.append("\nrecadded at: "+ rowPosition);
		initialValues.put("name", "DEF");
		initialValues.put("phone", "202");
		rowPosition= (int) db.insert("tblAMIGO", null, initialValues);
		txtMsg.append("\nrecadded at: "+ rowPosition);
		initialValues.clear();
		rowPosition= (int) db.insert("tblAMIGO", null, initialValues);
		txtMsg.append("\nrecadded at: "+ rowPosition);
		rowPosition= (int) db.insert("tblAMIGO", "name", initialValues);
		txtMsg.append("\nrecadded at: "+ rowPosition);
		useCursor1();
		
		}// useInsertMethod
	
	private void useUpdateMethod() {
		//using the update method to change name of selected friend
		String [] whereArgs= {"2", "7"};
		ContentValues updValues= new ContentValues();
		updValues.put("name", "Maria");
		int recAffected=db.update("tblAMIGO",
		updValues,
		"recID> ? and recID< ?",
		whereArgs);
		Toast.makeText(this, "Total7: "+ recAffected, 1).show();
		useCursor1();
		}
	
	private void useDeleteMethod() {
		//using the delete method to remove a group of friends
		//whose id# is between 2 and 7
		String [] whereArgs= {"2", "7"};
		int recAffected= db.delete("tblAMIGO",
		"recID> ? and recID< ?",
		whereArgs);
		Toast.makeText(this, "Total8: "+ recAffected, 1).show();
		useCursor1();
		}// useDeleteMethod
	
	
	 private static String DB_PATH = "/data/data/com.diaspark.gif/databases/";
	 
	    private static String DB_NAME = "FoodDatabase";
	 
	    private SQLiteDatabase myDataBase; 
	 
	 
	private void openRDatabase() {
		/*try{
		db= SQLiteDatabase.openDatabase(
				"/sdcard/FoodDatabase.db",			
		null,
		SQLiteDatabase.CREATE_IF_NECESSARY) ;
			
		Toast.makeText(this, "DB was opened!", 1).show();
		}
		catch(SQLiteException e) {
		Toast.makeText(this, e.getMessage(), 1).show();
		}
		
		
		String[] columns = {"Id", "Name"};
		
		
		Cursor c = db.rawQuery("SELECT * FROM vs_FoodNutrients LIMIT 5",null);
		
		int theTotal= c.getCount();
		Toast.makeText(this, "Total6: "+ theTotal, 1).show();
		int idCol= c.getColumnIndex("Id");
		int nameCol= c.getColumnIndex("Name");
		
		while(c.moveToNext()) {
		columns[0] = Integer.toString((c.getInt(idCol)));
		columns[1] = c.getString(nameCol);
		
		Toast.makeText(this, columns[0]+columns[1], Toast.LENGTH_LONG).show();
		}//createDatabase*/
		
		
		
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
		 DataBaseHelper myDbHelper = new DataBaseHelper(this);
	   
	 
	        try {
	 
	        	myDbHelper.createDataBase();
	 
	 	} catch (IOException ioe) {
	 
	 		throw new Error("Unable to create database");
	 
	 	}
	 
	 	try {
	 
	 		myDbHelper.openDataBase();
	 
	 	}catch(SQLException sqle){
	 
	 		throw sqle;
	 
	 	}
	 
    	
	}
	
	 private boolean checkDataBase(){
		 
	    	SQLiteDatabase checkDB = null;
	 
	    	try{
	    		String myPath = DB_PATH + DB_NAME;
	    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    	}catch(SQLiteException e){
	 
	    		//database does't exist yet.
	 
	    	}
	 
	    	if(checkDB != null){
	 
	    		checkDB.close();
	 
	    	}
	 
	    	return checkDB != null ? true : false;
	    }
	 
	 private void copyDataBase() throws IOException{
		 
	    	//Open your local db as the input stream
	    	InputStream myInput = getAssets().open(DB_NAME);
	 
	    	// Path to the just created empty db
	    	String outFileName = DB_PATH + DB_NAME;
	 
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
	 
	    }
	 
	    public void openDataBase() throws SQLException{
	 
	    	//Open the database
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    }
	   
	
	 
	
}
