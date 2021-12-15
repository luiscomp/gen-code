package singletons;

import ui.model.PreferencesModel;
import ui.model.ProjetoModel;

public class ProjSingleton {
	private static ProjSingleton instance;
	private static ProjetoModel projeto = new ProjetoModel();
	
	private static String pathDestino = "";
	
	public static synchronized ProjSingleton getInstance() {
		if(instance == null) {
			instance = new ProjSingleton();
		}
		return instance;
	}
	
	@Override
	public String toString() {
		return projeto.getSchemaDB();
	}

	public static PreferencesModel addToPrefs() {
		PrefsSingleton.getInstance();
		if(PrefsSingleton.getPrefs().getProjetos() != null && !PrefsSingleton.getPrefs().getProjetos().isEmpty()) {
			int indexProjeto = PrefsSingleton.getPrefs().getProjetos().indexOf(projeto);
			if(indexProjeto != -1) {
				PrefsSingleton.getPrefs().getProjetos().set(indexProjeto, projeto);
			} else {
				PrefsSingleton.getPrefs().addProjeto(projeto);
			}
		} else {
			PrefsSingleton.getPrefs().addProjeto(projeto);
		}
		
		return PrefsSingleton.getPrefs();
	}
	
	public static ProjetoModel getProjeto() { return projeto; }
	public static void setProjeto(ProjetoModel projeto) { ProjSingleton.projeto = projeto; }

	public static String getPathDestino() { return pathDestino; }
	public static void setPathDestino(String pathDestino) { ProjSingleton.pathDestino = pathDestino; }

	private ProjSingleton() {
		
	}
}