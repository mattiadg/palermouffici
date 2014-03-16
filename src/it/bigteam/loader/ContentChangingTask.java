package it.bigteam.loader;

import android.os.AsyncTask;
import android.support.v4.content.Loader;

/**
 * Questa classe ci consente di eseguire operazioni sul cambiamento di contenuto del loader
 * in background. Estenderemo questa classe per eseguire operazioni di lettura sui
 * dati in background.
 * @author mattiadigan
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public abstract class ContentChangingTask<T1, T2, T3> extends AsyncTask<T1, T2, T3> {

	private Loader<?> loader = null;
		
	public ContentChangingTask(Loader<?> loader) {
		this.loader = loader;
	}

	@Override
	protected void onPostExecute(T3 param) {
		loader.onContentChanged();
	}
}
