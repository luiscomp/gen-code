package ui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import enums.TipoChave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import singletons.ProjSingleton;
import ui.model.ObjectTree;
import ui.tasks.GerarCodigoTask;
import ui.tasks.RecuperarInfoBancoTask;
import ui.util.UtilsUI;
import vo.ColunaVO;
import vo.TabelaVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeradorController {
	private RecuperarInfoBancoTask recuperarInfoBancoTask;
	private GerarCodigoTask gerarCodigoTask;
	
	private ArrayList<TabelaVO> listaTabelas;
	private CheckBoxTreeItem<ObjectTree> itemRoot;
	
	@FXML private TitledPane tpBanco;
	@FXML private TreeView<ObjectTree> tvBanco;
	@FXML private ProgressIndicator pbProgressGerador;
	@FXML private ProgressIndicator piProgress;
	@FXML private Button btnSelecionarLocal;
	@FXML private Button btnGerarCodigo;
	@FXML private TextField txtPathDestino;
	@FXML private TextArea txtLog;
	
	@FXML private BorderPane bpProgress;
	
	@FXML private CheckBox cbColunaImagem;
	
	//------------------------------------------------
	// Referência do Objeto da Coluna Selecionada
	//------------------------------------------------
	private ColunaVO colunaSelecionada;
	
	@FXML
	private void initialize() {
		tpBanco = new TitledPane(ProjSingleton.getProjeto().getNome(), tpBanco);
		cbColunaImagem.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				colunaSelecionada.setImagem(newValue);
			}
		});
		
		desabilitarBotoes(true);
		recuperarListaTabelas();
	}
	
	@FXML
	private void salvarPrefs(ActionEvent event) {
		UtilsUI.savePrefs(ProjSingleton.addToPrefs());
		UtilsUI.mostrarAlert("Atenção", "Projeto salvo com sucesso.", AlertType.INFORMATION, getClass());
	}
	
	private void desabilitarBotoes(boolean value) {
		UtilsUI.desabilitarComponente(btnSelecionarLocal, value);
		UtilsUI.desabilitarComponente(btnGerarCodigo, value);
	}

	@FXML
	private void gerarCodigo(ActionEvent event) {
		if(!ProjSingleton.getPathDestino().isEmpty()) {
			UtilsUI.desabilitarComponente(btnGerarCodigo, true);
			
			ArrayList<TabelaVO> listaTabelasSelecionadas = pegarTabelasSelecionadas();
			
			gerarCodigoTask = new GerarCodigoTask(listaTabelasSelecionadas, txtLog);
			gerarCodigoTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					UtilsUI.zerarProgressBar(pbProgressGerador);
					UtilsUI.desabilitarComponente(btnGerarCodigo, false);
					executarBatchInstall();
				}
			});
			
			pbProgressGerador.progressProperty().unbind();
			pbProgressGerador.progressProperty().bind(gerarCodigoTask.progressProperty());
			
			new Thread(gerarCodigoTask).start();
		} else {
			UtilsUI.mostrarAlert("Atenção", "Selecione um local de destino", AlertType.INFORMATION, getClass());
		}
	}
	
	protected void executarBatchInstall() {
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" buildNode.bat");
		} catch (IOException e) {
			UtilsUI.mostrarExceptionDialog(e, getClass());
		}
	}

	@FXML
	private void selecionarPastaDestino(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Selecione um local de destino");
		
		File path = chooser.showDialog(UtilsUI.getStageFromEvent(event));
		
		if(path != null) {
			ProjSingleton.setPathDestino(path.getAbsolutePath());
			txtPathDestino.setText(path.getAbsolutePath());
		}
	}

	private ArrayList<TabelaVO> pegarTabelasSelecionadas() {
		ArrayList<TabelaVO> listaTabelas = new ArrayList<>();
		ArrayList<ColunaVO> listaColunas = null;
		TabelaVO tabela;
		for(TreeItem<ObjectTree> tabelaItem : itemRoot.getChildren()) {
			if(tabelaItem.getValue().isSelected()) {
				listaColunas = new ArrayList<>();
				
				for(TreeItem<ObjectTree> colunaItem : tabelaItem.getChildren()) {
					if(colunaItem.getValue().isSelected()) {
						listaColunas.add((ColunaVO) colunaItem.getValue().getObject());
					}
				}
				
				tabela = (TabelaVO) tabelaItem.getValue().getObject();
				tabela.setColunas(listaColunas);
				
				listaTabelas.add(tabela);
			}
		}
		
		return listaTabelas;
	}

	private void recuperarListaTabelas() {
		recuperarInfoBancoTask = new RecuperarInfoBancoTask();
		recuperarInfoBancoTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent arg0) {
				listaTabelas = recuperarInfoBancoTask.getValue();
				esconderProgresso(true);
				desabilitarBotoes(false);
				instanciarArvore();
			}
		});
		piProgress.progressProperty().unbind();
		piProgress.progressProperty().bind(recuperarInfoBancoTask.progressProperty());
		
		new Thread(recuperarInfoBancoTask).start();
	}

	private void esconderProgresso(boolean value) {
		bpProgress.setVisible(!value);
	}

	private void instanciarArvore() {
		itemRoot = new CheckBoxTreeItem<>(new ObjectTree(ProjSingleton.getInstance(), true));
		itemRoot.setExpanded(true);
		itemRoot.setSelected(true);
		itemRoot.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../assets/database.png"))));
		itemRoot.selectedProperty().addListener((obs, oldValue, newValue) -> {
			itemRoot.getValue().setSelected(newValue);
	    });
		
		for(TabelaVO tabela : listaTabelas) {
			CheckBoxTreeItem<ObjectTree> itemTabela = new CheckBoxTreeItem<>(new ObjectTree(tabela, true));
			itemTabela.setSelected(true);
			itemTabela.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../assets/table.png"))));
			itemTabela.selectedProperty().addListener((obs, oldValue, newValue) -> {
				itemTabela.getValue().setSelected(newValue);
		    });
			
			for(ColunaVO coluna : tabela.getColunas()) {
				CheckBoxTreeItem<ObjectTree> itemColuna = new CheckBoxTreeItem<>(new ObjectTree(coluna, true));
				itemColuna.setSelected(true);
				itemColuna.setGraphic(getIconeColuna(coluna));
				
				itemColuna.selectedProperty().addListener((obs, oldValue, newValue) -> {
					itemColuna.getValue().setSelected(newValue);
				});
				
				
				itemTabela.getChildren().add(itemColuna);
			}
			
			itemRoot.getChildren().add(itemTabela);
		}
		
		tvBanco.setCellFactory(CheckBoxTreeCell.<ObjectTree>forTreeView());
		tvBanco.setRoot(itemRoot);
		tvBanco.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	        @Override
	        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
	        	CheckBoxTreeItem<ObjectTree> itemSelecionado = (CheckBoxTreeItem<ObjectTree>) newValue;
	        	colunaSelecionada = (ColunaVO) itemSelecionado.getValue().getObject();
	        	
	        	cbColunaImagem.setSelected(colunaSelecionada.isImagem());
	        }
		});
	}

	private Node getIconeColuna(ColunaVO coluna) {
		switch (coluna.getTipoData()) {
			case INT:
			case BIGINT:
				if(coluna.getChave() == TipoChave.PRI) {
					return new ImageView(new Image(getClass().getResourceAsStream("../assets/pri_key.png")));
				} else if(coluna.getChave() == TipoChave.MUL) {
					return new ImageView(new Image(getClass().getResourceAsStream("../assets/ref_key.png")));
				} else {
					return new ImageView(new Image(getClass().getResourceAsStream("../assets/integer.png")));
				}
			case TEXT:
			case MEDIUMTEXT:
			case LONGTEXT:
			case VARCHAR:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/text.png")));
			case DATE:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/date.png")));
			case TIME:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/time.png")));
			case DATETIME:
			case TIMESTAMP:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/timestamp.png")));
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/decimal.png")));
			case ENUM:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/enum.png")));
			case TINYINT:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/boolean.png")));
			default:
				return new ImageView(new Image(getClass().getResourceAsStream("../assets/nao_mapeado.png")));
		}
	}
}
