package it.prova.gestionesmartphoneapp.dao.smartphone;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneDAO extends IBaseDAO<Smartphone>{
	
	public void updateVersioneOS (Long idSmartphone) throws Exception;

	public void deleteSmartphoneAfterDisinstalling2Apps (Long idSmartphone) throws Exception;
	
	public Smartphone findByIdEagerApps (Long idSmartphone) throws Exception;
}
