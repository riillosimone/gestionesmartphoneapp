package it.prova.gestionesmartphoneapp.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class MyTest {

	public static void main(String[] args) {

		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();

		try {

			System.out.println(
					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

//			testInserimentoNuovoSmartphone(smartphoneServiceInstance);
//			System.out.println(
//					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
//			initApp(appServiceInstance);
//			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");

//			testAggiornamentoVersioneOSSmartphone(smartphoneServiceInstance);
//			System.out.println(
//					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");

//			testAggiornamentoVersioneAppEDataAggiornamento(appServiceInstance);
//			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");

//			testRimozioneSmartphoneCompleta(smartphoneServiceInstance, appServiceInstance);
//			System.out.println(
//					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
//			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
			
//			testInstallaAppEsistenteASmartphoneEsistente(smartphoneServiceInstance, appServiceInstance);
//			System.out.println(
//					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
//			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
			
			testDisinstallaApp(smartphoneServiceInstance, appServiceInstance);
			System.out.println(
					"In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	private static void testInserimentoNuovoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("+++++++++  testInserimentoNuovoSmartphone INIZIO  +++++++++");
		Smartphone smartphoneInstance = new Smartphone("Motorola", "Moto G5", 299, 1);
		smartphoneServiceInstance.inserisciNuovo(smartphoneInstance);
		if (smartphoneInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoSmartphone FALLITO");

		System.out.println("+++++++++  testInserimentoNuovoSmartphone FINE  +++++++++");
	}

	private static void initApp(AppService appServiceInstance) throws Exception {
		if (appServiceInstance.cercaPerNome("Spotify") == null)
			appServiceInstance.inserisciNuovo(new App("Spotify", LocalDate.of(2020, 03, 19), LocalDate.now(), 5));
		if (appServiceInstance.cercaPerNome("Amazon") == null)
			appServiceInstance
					.inserisciNuovo(new App("Amazon", LocalDate.of(2021, 02, 14), LocalDate.of(2023, 01, 10), 5));
	}

	private static void testAggiornamentoVersioneOSSmartphone(SmartphoneService smartphoneServiceInstance)
			throws Exception {
		System.out.println("+++++++++  testAggiornamentoVersioneOSSmartphone INIZIO  +++++++++");
		List<Smartphone> listaSmartphonePresentiInElenco = smartphoneServiceInstance.listAll();
		if (listaSmartphonePresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testAggiornamentoVersioneOSSmartphone FALLITO: non sono presenti smartphone in elenco.");
		}

		Smartphone smartphoneDaAggiornare = listaSmartphonePresentiInElenco.get(0);
		System.out.println("Versione prima dell'aggiornamento: " + smartphoneDaAggiornare.getVersioneOS());

		LocalDateTime dateCreateDateTimeIniziale = smartphoneDaAggiornare.getCreateDateTime();
		LocalDateTime dateUpdateDateTimeIniziale = smartphoneDaAggiornare.getUpdateDateTime();

		if (!dateCreateDateTimeIniziale.equals(dateUpdateDateTimeIniziale)) {
			throw new RuntimeException("testAggiornamentoVersioneOSSmartphone FALLITO: le date non coincidono");
		}

		smartphoneServiceInstance.aggiornaVersioneOS(smartphoneDaAggiornare.getId());
		if (smartphoneDaAggiornare.getUpdateDateTime().isAfter(dateUpdateDateTimeIniziale))
			throw new RuntimeException(
					"testAggiornamentoVersioneOSSmartphone FALLITO: le date di modifica sono disallineate.");
		if (!smartphoneDaAggiornare.getCreateDateTime().equals(dateCreateDateTimeIniziale))
			throw new RuntimeException("testInserimentoNuovoSmartphone FALLITO: le date non coincidono");
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElemento(smartphoneDaAggiornare.getId());
		System.out.println("Versione dopo dell'aggiornamento: " + smartphoneReloaded.getVersioneOS());

		System.out.println("+++++++++  testAggiornamentoVersioneOSSmartphone FINE  +++++++++");
	}

	private static void testAggiornamentoVersioneAppEDataAggiornamento(AppService appServiceInstance) throws Exception {
		System.out.println("+++++++++  testAggiornamentoVersioneAppEDataAggiornamento INIZIO  +++++++++");
		List<App> listaAppPresentiInElenco = appServiceInstance.listAll();
		if (listaAppPresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testAggiornamentoVersioneAppEDataAggiornamento FALLITO: non sono presenti app in elenco.");
		}

		App appDaAggiornare = listaAppPresentiInElenco.get(0);
		System.out.println("Versione prima dell'aggiornamento: " + appDaAggiornare.getVersione());

		LocalDateTime dateCreateDateTimeIniziale = appDaAggiornare.getCreateDateTime();
		LocalDateTime dateUpdateDateTimeIniziale = appDaAggiornare.getUpdateDateTime();

		if (!dateCreateDateTimeIniziale.equals(dateUpdateDateTimeIniziale)) {
			throw new RuntimeException(
					"testAggiornamentoVersioneAppEDataAggiornamento FALLITO: le date non coincidono");
		}

		appServiceInstance.aggiornaVersioneAppEDataDiAggiornamento(appDaAggiornare.getId());
		if (appDaAggiornare.getUpdateDateTime().isAfter(dateUpdateDateTimeIniziale))
			throw new RuntimeException(
					"testAggiornamentoVersioneAppEDataAggiornamento FALLITO: le date di modifica sono disallineate.");
		if (!appDaAggiornare.getCreateDateTime().equals(dateCreateDateTimeIniziale))
			throw new RuntimeException("testInserimentoNuovoSmartphone FALLITO: le date non coincidono");
		App appReloaded = appServiceInstance.caricaSingoloElemento(appDaAggiornare.getId());
		System.out.println("Versione dopo dell'aggiornamento: " + appReloaded.getVersione());

		System.out.println("+++++++++  testAggiornamentoVersioneAppEDataAggiornamento FINE  +++++++++");
	}

	private static void testRimozioneSmartphoneCompleta(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance) throws Exception {
		System.out.println("+++++++++  testRimozioneSmartphoneCompleta INIZIO  +++++++++");
	
		Smartphone smartphoneDaEliminare = new Smartphone("LG", "One", 299, 2);
		smartphoneServiceInstance.inserisciNuovo(smartphoneDaEliminare);
		List<App> listaAppPresentiInElenco = appServiceInstance.listAll();
		if (listaAppPresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testRimozioneSmartphoneCompleta FALLITO: non sono presenti smartphone in elenco.");
		}
		
		App primaApp = listaAppPresentiInElenco.get(0);
		App secondaApp = listaAppPresentiInElenco.get(1);
		smartphoneServiceInstance.aggiungiApp(smartphoneDaEliminare, primaApp);
		smartphoneServiceInstance.aggiungiApp(smartphoneDaEliminare, secondaApp);
		
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElementoEagerFetchingApps(smartphoneDaEliminare.getId());
		if(smartphoneReloaded.getApps().size() != 2) {
			throw new RuntimeException(
					"testRimozioneSmartphoneCompleta FALLITO: non sono state aggiunte le app.");
		}
		
		smartphoneServiceInstance.rimozioneSmartphoneCompleta(smartphoneReloaded.getId());
		Smartphone smartphoneSupposedToBeRemoved = smartphoneServiceInstance.caricaSingoloElementoEagerFetchingApps(smartphoneDaEliminare.getId());
		if (smartphoneSupposedToBeRemoved != null) {
			throw new RuntimeException(
					"testRimozioneSmartphoneCompleta FALLITO: rimozione non avvenuta.");
		}
	
		System.out.println("+++++++++  testRimozioneSmartphoneCompleta FINE  +++++++++");
	}
	private static void testInstallaAppEsistenteASmartphoneEsistente(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception {
		System.out.println("+++++++++  testInstallaAppEsistenteASmartphoneEsistente INIZIO  +++++++++");
		List<Smartphone> listaSmartphonePresentiInElenco = smartphoneServiceInstance.listAll();
		if (listaSmartphonePresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testInstallaAppEsistenteASmartphoneEsistente FALLITO: non sono presenti smartphone in elenco.");
		}

		Smartphone smartphoneSuCuiInstallareLApp = listaSmartphonePresentiInElenco.get(0);
		List<App> listaAppPresentiInElenco = appServiceInstance.listAll();
		if (listaAppPresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testInstallaAppEsistenteASmartphoneEsistente FALLITO: non sono presenti smartphone in elenco.");
		}
		App appDaInstallare = listaAppPresentiInElenco.get(0);
		smartphoneServiceInstance.aggiungiApp(smartphoneSuCuiInstallareLApp, appDaInstallare);
		
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElementoEagerFetchingApps(smartphoneSuCuiInstallareLApp.getId());
		if(smartphoneReloaded.getApps().isEmpty()) {
			throw new RuntimeException(
					"testInstallaAppEsistenteASmartphoneEsistente FALLITO: non è stata aggiunta l'app.");
		}
		System.out.println("+++++++++  testInstallaAppEsistenteASmartphoneEsistente FINE  +++++++++");
		
	}
	
	private static void testDisinstallaApp(SmartphoneService smartphoneServiceInstance, AppService appServiceInstance) throws Exception {
		System.out.println("+++++++++  testInstallaAppEsistenteASmartphoneEsistente INIZIO  +++++++++");
		List<Smartphone> listaSmartphonePresentiInElenco = smartphoneServiceInstance.listAll();
		if (listaSmartphonePresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testDisinstallaApp FALLITO: non sono presenti smartphone in elenco.");
		}

		Smartphone smartphoneSuCuiDisinstallareLApp = listaSmartphonePresentiInElenco.get(0);
		List<App> listaAppPresentiInElenco = appServiceInstance.listAll();
		if (listaAppPresentiInElenco.size() < 1) {
			throw new RuntimeException(
					"testDisinstallaApp FALLITO: non sono presenti smartphone in elenco.");
		}
		App appDaDisinstallare = listaAppPresentiInElenco.get(0);
		smartphoneServiceInstance.rimuoviApp(smartphoneSuCuiDisinstallareLApp, appDaDisinstallare);
		
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElementoEagerFetchingApps(smartphoneSuCuiDisinstallareLApp.getId());
		if(!smartphoneReloaded.getApps().isEmpty()) {
			throw new RuntimeException(
					"testDisinstallaApp FALLITO: non è stata aggiunta l'app.");
		}
		System.out.println("+++++++++  testDisinstallaApp FINE  +++++++++");
		
	}
}
