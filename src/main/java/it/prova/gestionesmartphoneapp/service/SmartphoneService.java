package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService {

	public List<Smartphone> listAll() throws Exception;

	public Smartphone caricaSingoloElemento(Long id) throws Exception;
	
	public Smartphone caricaSingoloElementoEagerFetchingApps(Long id) throws Exception;

	public void aggiorna(Smartphone smartphoneInstance) throws Exception;

	public void aggiornaVersioneOS(Long idSmartphone) throws Exception;

	public void inserisciNuovo(Smartphone smartphoneInstance) throws Exception;

	public void rimuovi(Long idSmartphone) throws Exception;

	public void aggiungiApp(Smartphone smartphoneInstance, App appInstance) throws Exception;

	public void rimuoviApp(Smartphone smartphoneInstance, App appInstance) throws Exception;

	public void rimozioneSmartphoneCompleta(Long idSmartphone) throws Exception;

	// per injection
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);

	public void setAppDAO(AppDAO appDAO);
}
