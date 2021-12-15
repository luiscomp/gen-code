package ui.controller;

import dao.FabricaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import singletons.PrefsSingleton;
import singletons.ProjSingleton;
import ui.enums.Plataformas;
import ui.model.ProjetoModel;
import ui.util.UtilsUI;

public class ProjetosController {
	private ObservableList<ProjetoModel> projetos = FXCollections.observableArrayList();
	
	@FXML private TableView<ProjetoModel> tbProjetos;
	@FXML private TableColumn<ProjetoModel, String> clNome;
	@FXML private TableColumn<ProjetoModel, ImageView> clIconBackend;
	
	@FXML private TabPane tpConfig;
	@FXML private Button btnIniciar;

	@FXML private TextField txtNomeProjeto;
	@FXML private TextField txtUrlArquivos;
	@FXML private ComboBox<String> cbPlataforma;
	
	@FXML private TextField txtServidorDB;
	@FXML private TextField txtHostDB;
	@FXML private TextField txtUsuarioDB;
	@FXML private TextField txtSenhaDB;
	@FXML private TextField txtPortaDB;
	@FXML private TextField txtSchemaDB;
	
	@FXML
    private void initialize() {
		habilitarConfiguracao(false);
		
		tbProjetos.setItems(projetos);
		carregarPrefs();
		
		clNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		clIconBackend.setCellValueFactory(new PropertyValueFactory<ProjetoModel, ImageView>("iconPlataforma"));
		
		cbPlataforma.getItems().addAll(Plataformas.toArray());
		cbPlataforma.valueProperty().addListener((composant, oldValue, newValue) -> {
			if(newValue != null) {
				tbProjetos.getSelectionModel().getSelectedItem().setPlataforma(Plataformas.valueOf(newValue));
				tbProjetos.refresh();
			}
		});
		
		tbProjetos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			habilitarConfiguracao(true);
			exibirDetalhesProjeto(newValue);
		});
		
		txtNomeProjeto.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setNome(newValue);
		});
		txtUrlArquivos.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setUrlBaseArquivos(newValue);
		});
		txtServidorDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setServidorDB(newValue);
		});
		txtHostDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setHostDB(newValue);
		});
		txtUsuarioDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setUsuarioDB(newValue);
		});
		txtSenhaDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setSenhaDB(newValue);
		});
		txtPortaDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setPortaDB(newValue);
		});
		txtSchemaDB.textProperty().addListener((observable, oldValue, newValue) -> {
			tbProjetos.getSelectionModel().getSelectedItem().setSchemaDB(newValue);
		});
    }
	
	private void carregarPrefs() {
		UtilsUI.loadPrefs();
		if(PrefsSingleton.getPrefs().getProjetos() != null) {
			projetos.addAll(PrefsSingleton.getPrefs().getProjetos());
		}
	}

	public void abrirTela() {
		UtilsUI.abrirTela(AnchorPane.class, getClass(), "../view/ProjetosView.fxml");
	}

	private void habilitarConfiguracao(boolean habilitar) {
		tpConfig.setDisable(!habilitar);
		btnIniciar.setDisable(!habilitar);
	}

	@FXML
	private void novoProjeto(ActionEvent event) {
		ProjetoModel projeto = new ProjetoModel("Novo Projeto", "3306");
		projeto.setUrlValidarAcesso("/usuario/login");
		
		projetos.add(projeto);
		tbProjetos.getSelectionModel().selectLast();
	}
	
	@FXML
	private void excluirProjeto(ActionEvent event) {
		PrefsSingleton.getPrefs().getProjetos().remove(tbProjetos.getSelectionModel().getSelectedItem());
		UtilsUI.savePrefs(PrefsSingleton.getPrefs());
		
		projetos.remove(tbProjetos.getSelectionModel().getSelectedItem());
		
		if(projetos.isEmpty()) {
			habilitarConfiguracao(false);
		}
	}
	
	private void exibirDetalhesProjeto(ProjetoModel projeto) {
		if(projeto != null) {
			txtNomeProjeto.setText(projeto.getNome());
			txtUrlArquivos.setText(projeto.getUrlBaseArquivos());
			if(projeto.getPlataforma() != null) {
				cbPlataforma.getSelectionModel().select(projeto.getPlataforma());
			} else {
				cbPlataforma.getSelectionModel().clearSelection();
			}
			txtServidorDB.setText(projeto.getServidorDB());
			txtHostDB.setText(projeto.getHostDB());
			txtUsuarioDB.setText(projeto.getUsuarioDB());
			txtSenhaDB.setText(projeto.getSenhaDB());
			txtPortaDB.setText(projeto.getPortaDB());
			txtSchemaDB.setText(projeto.getSchemaDB());
		} else {
			cbPlataforma.getSelectionModel().clearSelection();
		}
	}
	
	@FXML
	private void iniciarGerador(ActionEvent event) {
		if(dadosValidos()) {
			ProjSingleton.getInstance();
			ProjSingleton.setProjeto(tbProjetos.getSelectionModel().getSelectedItem());
			
			FabricaDAO.setarParametrosConexao(ProjSingleton.getProjeto().getServidorDB(), ProjSingleton.getProjeto().getUsuarioDB(), ProjSingleton.getProjeto().getSenhaDB());
			
			try {
				if(FabricaDAO.criarConexaoSimples() != null) {
					UtilsUI.abrirTela(AnchorPane.class, getClass(), "../view/GeradorCodigoView.fxml");
					UtilsUI.fecharTela(event);
				} else {
					UtilsUI.mostrarAlert("Conexão MySQL", "Falha na Conexão!", AlertType.ERROR, getClass());
				}
			} catch (Exception e) {
				UtilsUI.mostrarExceptionDialog(e, getClass());
			}
		}
	}

	private boolean dadosValidos() {
		StringBuffer mensagem = new StringBuffer();
		
		if(txtNomeProjeto.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Nome do Projeto.\n");
		}
		if(txtUrlArquivos.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo URL base dos arquivos.\n");
		}
		if(cbPlataforma.getSelectionModel().isEmpty()) {
			mensagem.append("Favor, informe o uma Plataforma de Desenvolvimento.\n");
		}
		if(txtServidorDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Servidor / IP.\n");
		}
		if(txtHostDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Host.\n");
		}
		if(txtUsuarioDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Usuario.\n");
		}
		if(txtSenhaDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Senha.\n");
		}
		if(txtPortaDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Porta.\n");
		}
		if(txtSchemaDB.getText().isEmpty()) {
			mensagem.append("Favor, informe o campo Schema.");
		}
		
		if(mensagem.length() > 0) {
			UtilsUI.mostrarAlert("Atenção", mensagem.toString(), AlertType.INFORMATION, getClass());
			return false;
		}
		
		return true;
	}
}
