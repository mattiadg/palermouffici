package it.bigteam;

import it.bigteam.R;
import it.bigteam.database.DatabaseHelper;
import it.bigteam.database.UfficioDataSource;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_view);
		DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
		SQLiteDatabase database = dbHelper.openDataBase();
		UfficioDataSource dataSource = new UfficioDataSource(database);
		dbHelper.close();
	}

}
