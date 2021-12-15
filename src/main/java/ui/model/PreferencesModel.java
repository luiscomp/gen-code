package ui.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "preferences")
public class PreferencesModel {
	private ArrayList<ProjetoModel> projetos;

	@XmlElementWrapper(name = "projetos")
	@XmlElement(name = "projeto")
	public ArrayList<ProjetoModel> getProjetos() { return projetos; }
	public void setProjetos(ArrayList<ProjetoModel> projetos) { this.projetos = projetos; }
	
	
	public void addProjeto(ProjetoModel projeto) {
		if(projetos == null) {
			projetos = new ArrayList<>();
		}
		projetos.add(projeto);
	}
}
