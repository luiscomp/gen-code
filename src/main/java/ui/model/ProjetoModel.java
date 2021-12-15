package ui.model;

import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import ui.enums.Plataformas;

@XmlRootElement(name = "Projeto")
public class ProjetoModel {
	private StringProperty nome;
	private Plataformas plataforma;
	private StringProperty urlValidarAcesso;
	private StringProperty urlBaseArquivos;
	
	private StringProperty servidorDB;
	private StringProperty hostDB;
	private StringProperty portaDB;
	private StringProperty usuarioDB;
	private StringProperty senhaDB;
	private StringProperty schemaDB;
	
	public ProjetoModel() {
		super();
	}
	
	public ProjetoModel(String nome, String porta) {
		super();
		this.nome = new SimpleStringProperty(nome);
		this.portaDB = new SimpleStringProperty(porta);
	}
	
	public ImageView getIconPlataforma() { return this.plataforma != null ? new ImageView(this.plataforma.getUrlIcon().toString()) : null; }
	public String getPlataforma() { return this.plataforma != null ? this.plataforma.name() : null; }
	public void setPlataforma(Plataformas plataforma) { this.plataforma = plataforma; }
	public void setPlataforma(String plataforma) { this.plataforma = Plataformas.valueOf(plataforma); }

	public String getNome() { return nome != null ? nome.get() : ""; }
	public StringProperty getNomeProperty() { return nome; }
	public void setNome(String nome) { 
		if(this.nome != null) {
			this.nome.set(nome); 
		} else {
			this.nome = new SimpleStringProperty(nome);
		}
	}
	
	public String getUrlValidarAcesso() { return urlValidarAcesso != null ? urlValidarAcesso.get() : ""; }
	public StringProperty getUrlValidarAcessoProperty() { return urlValidarAcesso; }
	public void setUrlValidarAcesso(String urlValidarAcesso) { 
		if(this.urlValidarAcesso != null) {
			this.urlValidarAcesso.set(urlValidarAcesso); 
		} else {
			this.urlValidarAcesso = new SimpleStringProperty(urlValidarAcesso);
		}
	}
	
	public String getUrlBaseArquivos() { return urlBaseArquivos != null ? urlBaseArquivos.get() : ""; }
	public StringProperty getUrlBaseArquivosProperty() { return urlBaseArquivos; }
	public void setUrlBaseArquivos(String urlBaseArquivos) { 
		if(this.urlBaseArquivos != null) {
			this.urlBaseArquivos.set(urlBaseArquivos); 
		} else {
			this.urlBaseArquivos = new SimpleStringProperty(urlBaseArquivos);
		}
	}
	
	public String getServidorDB() { return servidorDB != null ? servidorDB.get() : ""; }
	public StringProperty getServidorDBProperty() { return servidorDB; }
	public void setServidorDB(String servidorDB) { 
		if(this.servidorDB != null) {
			this.servidorDB.set(servidorDB); 
		} else {
			this.servidorDB = new SimpleStringProperty(servidorDB);
		}
	}

	public String getHostDB() { return hostDB != null ? hostDB.get() : ""; }
	public StringProperty getHostDBProperty() { return hostDB; }
	public void setHostDB(String hostDB) { 
		if(this.hostDB != null) {
			this.hostDB.set(hostDB); 
		} else {
			this.hostDB = new SimpleStringProperty(hostDB);
		}
	}

	public String getPortaDB() { return portaDB != null ? portaDB.get() : ""; }
	public StringProperty getPortaDBProperty() { return portaDB; }
	public void setPortaDB(String portaDB) { 
		if(this.portaDB != null) {
			this.portaDB.set(portaDB); 
		} else {
			this.portaDB = new SimpleStringProperty(portaDB);
		}
	}

	public String getUsuarioDB() { return usuarioDB != null ? usuarioDB.get() : ""; }
	public StringProperty getUsuarioDBProperty() { return usuarioDB; }
	public void setUsuarioDB(String usuarioDB) { 
		if(this.usuarioDB != null) {
			this.usuarioDB.set(usuarioDB); 
		} else {
			this.usuarioDB = new SimpleStringProperty(usuarioDB);
		}
	}

	public String getSenhaDB() { return senhaDB != null ? senhaDB.get() : ""; }
	public StringProperty getSenhaDBProperty() { return senhaDB; }
	public void setSenhaDB(String senhaDB) { 
		if(this.senhaDB != null) {
			this.senhaDB.set(senhaDB); 
		} else {
			this.senhaDB = new SimpleStringProperty(senhaDB);
		}
	}

	public String getSchemaDB() { return schemaDB != null ? schemaDB.get() : ""; }
	public StringProperty getSchemaDBProperty() { return schemaDB; }
	public void setSchemaDB(String schemaDB) { 
		if(this.schemaDB != null) {
			this.schemaDB.set(schemaDB); 
		} else {
			this.schemaDB = new SimpleStringProperty(schemaDB);
		}
	}
}
