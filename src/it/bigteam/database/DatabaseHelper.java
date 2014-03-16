package it.bigteam.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "uffici.db";
	private static final String DATABASE_PATH = "/data/data/it.bigteam/databases/";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase myDataBase;
	private final Context context;
	private static DatabaseHelper sInstance;
	
	public static DatabaseHelper getInstance(Context context){
		// Use the application context, which will ensure that you 
	    // don't accidentally leak an Activity's context.
	    // See this article for more information: http://bit.ly/6LRzfx
	    if (sInstance == null) {
	      sInstance = new DatabaseHelper(context);
	    }
	    return sInstance;
	}
	
	//Builder
	private DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	/**Crea un database vuoto e lo riscrive */
	 public void createDataBase() throws IOException{
		 
		 boolean dbExist = checkDataBase();
		  
		 if(dbExist){
			 /* Database exists, do nothing */
		 }else{
		 //By calling this method and empty database will be created into the default system path
		 //of your application so we are gonna be able to overwrite that database with our database.
			 this.getReadableDatabase();
			 try {
				 copyDataBase(); 
			 } catch (IOException e) { 
				 throw new Error("Error copying database"); 
			 }
		 } 
	 }
	 
	 /**
	  * Check if the database already exist to avoid re-copying the file each time you open the application.
	  * @return true if it exists, false if it doesn't
	  */
	private boolean checkDataBase(){ 
		SQLiteDatabase checkDB = null;	 
		try{
				String myPath = DATABASE_PATH + DATABASE_NAME;
				checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
				System.out.println("DB TROVATOOOOOOOOOOOOOOO!!!!!!!!!!");
		}catch(SQLiteException e){
	//database does't exist yet.
			System.out.println("DB NON TROVATOOOOOOOOOOOOOOO!!!!!!!!!!");
		}
		if(checkDB != null){ 
			checkDB.close();
		}
	 	return checkDB != null ? true : false;
	}
	
	 /**
	  * Copies your database from your local assets-folder to the just created empty database in the
	  * system folder, from where it can be accessed and handled.
	  * This is done by transfering bytestream.
	  * */
	private void copyDataBase() throws IOException{
	//Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DATABASE_NAME);
	 
	// Path to the just created empty db
		String outFileName = DATABASE_PATH + DATABASE_NAME;
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
	
	 public SQLiteDatabase openDataBase() throws SQLException{	 
		//Open the database
		String myPath = DATABASE_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		return myDataBase;
	}

	 @Override
	 public synchronized void close() {
	 	 if(myDataBase != null)
	 		 myDataBase.close();
	  	 super.close();
	 }
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public void onOpen(SQLiteDatabase db){
		
	}

}
