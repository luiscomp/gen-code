package singletons;

import ui.model.PreferencesModel;

public class PrefsSingleton {
	private static PrefsSingleton instance;
	
	private static PreferencesModel prefs = new PreferencesModel();
	
	public static synchronized PrefsSingleton getInstance() {
		if(instance == null) {
			instance = new PrefsSingleton();
		}
		return instance;
	}
	
	public static PreferencesModel getPrefs() { return prefs; }
	public static void setPrefs(PreferencesModel prefs) { PrefsSingleton.prefs = prefs; }

	private PrefsSingleton() {
		
	}
}