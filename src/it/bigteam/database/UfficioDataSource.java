package it.bigteam.database;

import it.bigteam.model.Ufficio;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author mattiadigan
 *
 */
public class UfficioDataSource extends DataSource<Ufficio> {

	//Nome della tabella del database
	private static final String DATABASE_TABLE = "Uffici";
	//Nomi dei campi del database
	public static final String KEY_ID = "_Id";
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
	
	/**
	 * Costruttore
	 * @param database Database da cui leggere i dati
	 */
	public UfficioDataSource(SQLiteDatabase database){
		super(database);
	}

	@Override
	public List<Ufficio> read() {
		Cursor cursor = mDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
		List<Ufficio> uffici = new ArrayList<Ufficio>();
		if(cursor != null && cursor.moveToFirst()){
			while(!cursor.isAfterLast()){
				uffici.add(generateObjectFromCursor(cursor));
				cursor.moveToNext();
			}
			cursor.close();
		}
		return uffici;
	}

	@Override
	public List<Ufficio> read(String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor cursor = mDatabase.query(DATABASE_TABLE, getAllColumns(), selection, selectionArgs, groupBy, having, orderBy);
		List<Ufficio> uffici = new ArrayList<Ufficio>();
		if(cursor != null && cursor.moveToFirst()){
			while(!cursor.isAfterLast()){
				uffici.add(generateObjectFromCursor(cursor));
				cursor.moveToNext();
			}
			cursor.close();
		}
 		return uffici;
	}
	
	/**
	 * @return Un array di stringhe contenente i nomi di tutte le colonne
	 */
	private String[] getAllColumns() {
		return new String[] { KEY_ID, KEY_AREA, KEY_SETTORE, KEY_EMAIL_SETTORE, KEY_EMAIL_SETTORE_CERT, KEY_SERVIZIO, KEY_INDIRIZZO, KEY_TELEFONO, KEY_FAX, KEY_EMAIL_SERVIZIO, KEY_EMAIL_SERVIZIO_CERT };
	}
	
	/**
	 * Crea un nuovo oggetto di tipo Ufficio a partire da un Cursor
	 * @param cursor Cursore contenente la query dal database
	 * @return Un nuovo oggetto contenente una riga della query
	 */
	private Ufficio generateObjectFromCursor(Cursor cursor) {
		if(cursor == null){
			return null;
		}
		String id = cursor.getString(cursor.getColumnIndex(KEY_ID));
		String area = cursor.getString(cursor.getColumnIndex(KEY_AREA));
		String settore = cursor.getString(cursor.getColumnIndex(KEY_SETTORE));
		String email_set = cursor.getString(cursor.getColumnIndex(KEY_EMAIL_SETTORE));
		String email_cert_set = cursor.getString(cursor.getColumnIndex(KEY_EMAIL_SETTORE_CERT));
		String servizio = cursor.getString(cursor.getColumnIndex(KEY_SERVIZIO));
		String indirizzo = cursor.getString(cursor.getColumnIndex(KEY_INDIRIZZO));
		String telefono = cursor.getString(cursor.getColumnIndex(KEY_TELEFONO));
		String fax = cursor.getString(cursor.getColumnIndex(KEY_FAX));
		String email_ser = cursor.getString(cursor.getColumnIndex(KEY_EMAIL_SERVIZIO));
		String email_cert_ser = cursor.getString(cursor.getColumnIndex(KEY_EMAIL_SERVIZIO_CERT));
		Ufficio ufficio = new Ufficio(id, area, settore, email_set, email_cert_set, servizio, indirizzo, telefono, fax, email_ser, email_cert_ser);
		return ufficio;		
	}
}
