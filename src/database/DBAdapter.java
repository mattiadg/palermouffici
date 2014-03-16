package database;

import it.bigteam.database.DatabaseHelper;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * 
 * @author mattiadigan
 *
 */
public class DBAdapter {
	@SuppressWarnings("unused")
	private static final String LOG_TAG = DBAdapter.class.getName();
	
	private Context context;
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	
	//Database fields
	private static final String DATABASE_TABLE = "Uffici";
	public static final String KEY_ID = "id";
	public static final String KEY_AREA = "Area";
	public static final String KEY_SETTORE = "Settore";
	public static final String KEY_EMAIL_SETTORE = "Email_set";
	public static final String KEY_EMAIL_SETTORE_CERT = "Email_cert_set";
	public static final String KEY_SERVIZIO = "Servizio";
	public static final String KEY_INDIRIZZO = "Indirizzo";
	public static final String KEY_TELEFONO = "Telefono";
	public static final String KEY_FAX = "Fax";
	public static final String KEY_EMAIL_SERVIZIO = "Email_ser";
	public static final String KEY_EMAIL_SERVIZIO_CERT = "Email_cert_ser";

	//Builder
	public DBAdapter(Context context){
		this.context = context;
	}
	
	//Opens the database
	public DBAdapter open() throws SQLException{
		
		dbHelper = DatabaseHelper.getInstance(context);
		 try {
			 
			 dbHelper.createDataBase();
			  
			 } catch (IOException ioe) {
			  
			 throw new Error("Unable to create database");
			  
			 }
			  
			 try {
			  
			 database = dbHelper.openDataBase();
			  
			 }catch(SQLException sqle){
			  
			 throw sqle;
			  
			 }
		return this;
	}
	
	//Close the database
	public void close(){
		dbHelper.close();
	}
	
	//Fetch all database rows
	public Cursor fetchAllRows(){
		Cursor cursor = null;
		try{
			cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
			Toast toast = Toast.makeText(context, Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT);
			toast.show();
		}
		catch(SQLException e){
			Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
			toast.show();
		}
		finally{
			close();
		}
		return cursor;
	}
}
