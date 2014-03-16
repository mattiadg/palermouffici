package it.bigteam.loader;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class AbstractDataLoader<E extends List<?>> extends AsyncTaskLoader<E> {
	protected E mLastDataList = null;
	
	protected abstract E buildList();
	public AbstractDataLoader(Context context){
		super(context);
	}
	
	/**
	 * Viene eseguito in un worker thread che carica i nostri dati. Delega il lavoro al metodo
	 * buildList() della sottoclasse concreta.
	 */
	@Override
	public E loadInBackground(){
		return buildList();
	}
	
	/**
	 * Viene eseguito nel thread dell'UI, trasferendo i dati dal thread di background a
	 * qualunque cosa stia usando i dati
	 */
	@Override
	public void deliverResult(E dataList){
		if(isReset()){
			//Una query async è arrivata mentre il loader è stopped
			emptyDataList(dataList);
			return;
		}
		E oldDataList = mLastDataList;
		mLastDataList = dataList;
		if(isStarted()){
			super.deliverResult(dataList);
		}
		if(oldDataList != null && oldDataList != dataList && oldDataList.size() > 0){
			emptyDataList(oldDataList);
		}
	}
	
	/**
	 * Inizia una caricamento asincrono della lista dei dati.
	 * Quando il risultato è pronto, le callbacks saranno chiamate nel thread UI. Se un
	 * caricamento precedente è stato completato ed è ancora valido, il risultato può essere
	 * passato alle callbacks immediatamente.
	 */
	@Override
	protected void onStartLoading(){
		if(mLastDataList != null){
			deliverResult(mLastDataList);
		}
		if(takeContentChanged() || mLastDataList == null || mLastDataList.size() == 0){
			forceLoad();
		}
	}
	
	/**
	 * Deve essere chiamato dal thread UI, innescato da una chiamata a stopLoading().
	 */
	@Override
	protected void onStopLoading(){
		//Tenta l'annullamento del caricamento corrente, se possibile
		cancelLoad();
	}
	
	/**
	 * Deve essere chiamato dal thread UI, innescato da una chiamata a cancel().
	 * Qua ci assicuriamo che il Cursor venga chiuso, se esiste ancora e non è già chiuso.
	 */
	@Override
	public void onCanceled(E dataList){
		if(dataList != null && dataList.size() > 0){
			emptyDataList(dataList);
		}
	}
	
	/**
	 * Deve essere chiamato dal thread UI, innescato da una chiamata a reset().
	 * Qua ci assicuriamo che il Cursor venga chiuso, se esiste ancora e non è già chiuso.
	 */
	@Override
	protected void onReset(){
		super.onReset();
		//Si assicura che il loader sia stopped
		onStopLoading();
		if(mLastDataList != null && mLastDataList.size() > 0){
			emptyDataList(mLastDataList);
		}
		mLastDataList = null;
	}
	
	protected void emptyDataList(E dataList){
		if(dataList != null && dataList.size() > 0){
			for(int i = 0; i < dataList.size(); i++){
				dataList.remove(i);
			}
		}
	}
}
