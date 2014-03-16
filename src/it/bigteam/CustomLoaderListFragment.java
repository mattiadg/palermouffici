package it.bigteam;

import it.bigteam.database.DatabaseHelper;
import it.bigteam.database.UfficioDataSource;
import it.bigteam.loader.SQLiteUfficioDataLoader;
import it.bigteam.model.Ufficio;

import java.util.List;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.widget.ArrayAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomLoaderListFragment extends ListFragment implements LoaderCallbacks<List<Ufficio>> {
	
	private ArrayAdapter<Ufficio> mAdapter;
	//L'id del Loader(questo id è specifico del LoaderManager del ListFragment
	private static final int LOADER_ID = 1;
	private static final boolean DEBUG = true;
	private static final String TAG = "CustomLoaderListFragment";
	private SQLiteDatabase mDatabase;
	private UfficioDataSource mDataSource;
	private DatabaseHelper mDbHelper;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		mDbHelper = DatabaseHelper.getInstance(getActivity());
		mDatabase = mDbHelper.getReadableDatabase();
		mDataSource = new UfficioDataSource(mDatabase);
		mAdapter = new ArrayAdapter<Ufficio>(getActivity(), android.R.layout.simple_list_item_1);
		setEmptyText("Dati assenti!");
		setListAdapter(mAdapter);
		setListShown(false);
		if(DEBUG){
			Log.i(TAG, "++ Calling initLoader()! ++");
			if(getLoaderManager().getLoader(LOADER_ID) == null){
				Log.i(TAG, "+++ Initializing the new Loader... +++");
	        } else {
	          Log.i(TAG, "+++ Reconnecting with existing Loader (id '1')... +++");
	        }
		}
		// Initialize a Loader with id '1'. If the Loader with this id already
		// exists, then the LoaderManager will reuse the existing Loader.
		getLoaderManager().initLoader(LOADER_ID, null, this);
	}
	@Override
	public SQLiteUfficioDataLoader onCreateLoader(int id, Bundle args) {
		SQLiteUfficioDataLoader loader = new SQLiteUfficioDataLoader(getActivity(), mDataSource, null, null, null, null, null);
		return loader;
	}
	@Override
	public void onLoadFinished(android.support.v4.content.Loader<List<Ufficio>> loader, List<Ufficio> data) {
		 if (DEBUG) Log.i(TAG, "+++ onLoadFinished() called! +++");
	     mAdapter.clear(); 
		 for(Ufficio Ufficio : data){
			 mAdapter.add(Ufficio);
		 }
	     if (isResumed()) {
	       setListShown(true);
	     } else {
	       setListShownNoAnimation(true);
	     }
	}
	@Override
	public void onLoaderReset(android.support.v4.content.Loader<List<Ufficio>> arg0) {
		mAdapter.clear();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		mDbHelper.close();
		mDatabase.close();
		mDataSource = null;
		mDbHelper = null;
		mDatabase = null;
	}
}