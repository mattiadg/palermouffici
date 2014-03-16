package it.bigteam.model;

/**
 * Classe per creare oggetti di tipo ufficio dai dati del database
 * @author mattiadigan
 * 
 */

public class Ufficio {
	
	//Campi del database
	private String _id;
	private String area;
	private String settore;
	private String email_settore;
	private String email_cert_settore;
	private String servizio;
	private String indirizzo;
	private String telefono;
	private String fax;
	private String email_servizio;
	private String email_cert_servizio;
	
	/**
	 * Costruttore senza parametri. Crea un oggetto vuoto.
	 */
	public Ufficio(){ };
	
	public Ufficio(String id, String area, String settore, String email_settore, String email_cert_settore, String servizio, String indirizzo, String telefono, String fax, String email_ser, String email_cert_ser){
		this._id = id;
		this.area = area;
		this.settore = settore;
		this.email_settore = email_settore;
		this.email_cert_settore = email_cert_settore;
		this.servizio = servizio;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.fax = fax;
		this.email_servizio = email_ser;
		this.email_cert_servizio = email_cert_ser;
	}
	
	//Metodi getter
	public String getId(){
		return _id;
	}
	
	public String getArea(){
		return area;
	}
	
	public String getSettore(){
		return settore;
	}
	
	public String getEmailSettore(){
		return email_settore;
	}
	
	public String getEmailCertSettore(){
		return email_cert_settore;
	}
	
	public String getServizio(){
		return servizio;
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public String getFax(){
		return fax;
	}
	
	public String getEmailServizio(){
		return email_servizio;
	}
	
	public String getEmailCertServizio(){
		return email_cert_servizio;
	}
	
	@Override
	public String toString(){
		return area + " " + settore;
	}
}
