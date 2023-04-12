package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class MyTest {

	public static void main(String[] args) {

		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();

		try {
			
			System.out.println("In tabella Smartphone ci sono "+ smartphoneServiceInstance.listAll().size()+ " elementi.");
			System.out.println("In tabella App ci sono "+ appServiceInstance.listAll().size()+ " elementi.");

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			
			testInserimentoNuovoSmartphone(smartphoneServiceInstance);
			System.out.println("In tabella Smartphone ci sono "+ smartphoneServiceInstance.listAll().size()+ " elementi.");
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	private static void testInserimentoNuovoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
		System.out.println("+++++++++  testInserimentoNuovoSmartphone INIZIO  +++++++++");
		Smartphone smartphoneInstance = new Smartphone("Motorola","Moto G5",299,1);
		smartphoneServiceInstance.inserisciNuovo(smartphoneInstance);
		if (smartphoneInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoSmartphone FALLITO");
		
		System.out.println("+++++++++  testInserimentoNuovoSmartphone FINE  +++++++++");
	}

}
