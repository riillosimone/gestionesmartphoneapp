package it.prova.gestionesmartphoneapp.dao.app;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppDAO extends IBaseDAO<App>{
	
	public App findByNome (String nome) throws Exception;

	public void updateVersioneEDataAggiornamento (Long idApp) throws Exception;
}
