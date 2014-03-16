package it.bigteam.database;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;

/** 
 * Classe astratta generica per eseguire operazioni di lettura da una fonte di dati
 * @param <E> Tipo di dato della fonte di dati
 * @author mattiadigan
 */
public abstract class DataSource<E>{
	/**Database da cui leggere i dati*/
	protected SQLiteDatabase mDatabase;
	
	/**
	 * @param database Database da cui leggere i dati
	 */
	public DataSource(SQLiteDatabase database){
		mDatabase = database;
	}
	
	/**Legge tutti i record dal database
	 * 
	 * @return Una lista con tutti i record
	 */
	public abstract List<E> read();
	
	/**
	 * @param selection			Filtro per selezionare le righe. Formattato come una clausola SQL WHERE
	 * @param selectionArgs		Puoi include ?s in selection,in quel caso gli argomenti vengono sostiuiti con selectionArgs
	 * @param groupBy			Campi su raggruppare, come in una SQL GROUPBY
	 * @param having			Analogo della clausola having SQL
	 * @param orderBy			Analogo della clausola SQL ORDERBY
	 * @return	Una lista con le righe del database che soddisfano la query
	 */
	public abstract List<E> read(String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
}
