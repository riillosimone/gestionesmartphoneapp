package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.MyFactoryDAO;

public class MyServiceFactory {
	private static SmartphoneService SMARTPHONE_SERVICE_INSTANCE;
	private static AppService APP_SERVICE_INSTANCE;

	
	public static SmartphoneService getSmartphoneServiceInstance() {
		if(SMARTPHONE_SERVICE_INSTANCE == null)
			SMARTPHONE_SERVICE_INSTANCE = new SmartphoneServiceImpl();
		
		SMARTPHONE_SERVICE_INSTANCE.setSmartphoneDAO(MyFactoryDAO.getSmartphoneDAOInstance());
		SMARTPHONE_SERVICE_INSTANCE.setAppDAO(MyFactoryDAO.getAppDAOInstance());
		return SMARTPHONE_SERVICE_INSTANCE;
	}
	
	public static AppService getAppServiceInstance() {
		if(APP_SERVICE_INSTANCE == null)
			APP_SERVICE_INSTANCE = new AppServiceImpl();
		
		APP_SERVICE_INSTANCE.setSmartphoneDAO(MyFactoryDAO.getSmartphoneDAOInstance());
		APP_SERVICE_INSTANCE.setAppDAO(MyFactoryDAO.getAppDAOInstance());
		return APP_SERVICE_INSTANCE;
	}
}
