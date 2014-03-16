package it.bigteam.loader;

import it.bigteam.database.DataSource;
import it.bigteam.model.Ufficio;
import java.util.List;

import android.content.Context;

public class SQLiteUfficioDataLoader extends AbstractDataLoader<List<Ufficio>> {
	private DataSource<Ufficio> mDataSource;
	private String mSelection;
	private String[] mSelectionArgs;
	private String mGroupBy;
	private String mHaving;
	private String mOrderBy;
	
	public SQLiteUfficioDataLoader(Context context, DataSource<Ufficio> dataSource, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		super(context);
		mDataSource = dataSource;
		mSelection = selection;
		mSelectionArgs = selectionArgs;
		mGroupBy = groupBy;
		mHaving = having;
		mOrderBy = orderBy;
	}

	@Override
	protected List<Ufficio> buildList() {
		List<Ufficio> ufficioList = mDataSource.read(mSelection, mSelectionArgs, mGroupBy, mHaving, mOrderBy);
		return ufficioList;
	}

}
