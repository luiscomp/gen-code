package ui.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import singletons.PrefsSingleton;
import singletons.ProjSingleton;
import ui.model.PreferencesModel;

public class UtilsUI {
	public static void mostrarAlert(String titulo, String mensagem, AlertType severidade, Class<?> contexto) {
		Alert alert = new Alert(severidade);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(contexto.getResourceAsStream("../application/icon.png")));
		
		alert.showAndWait();
	}
	
	public static void mostrarExceptionDialog(Exception e, Class<?> contexto) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception");
		alert.setHeaderText(null);
		alert.setContentText(e.toString());

		Exception ex = new FileNotFoundException(e.getMessage());

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("Exception stacktrace:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(contexto.getResourceAsStream("../application/icon.png")));
		
		alert.showAndWait();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void abrirTela(Class<T> layout, Class<?> contexto, String urlView) {
		try {
			T geradorLayout = (T) FXMLLoader.load(contexto.getResource(urlView));
			Scene geradorScene = new Scene((Parent) geradorLayout); 
			geradorScene.getStylesheets().add(contexto.getResource("../application/application.css").toExternalForm());
			
			Stage geradorStage = new Stage();
			geradorStage.setTitle(String.join(" • ", ConstantesUI.appName, ProjSingleton.getProjeto().getNome()));
			geradorStage.setScene(geradorScene);
			geradorStage.setResizable(false);
			geradorStage.initStyle(StageStyle.UNIFIED);
			geradorStage.getIcons().add(new Image(contexto.getResourceAsStream("../application/icon.png")));
			geradorStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fecharTela(Event event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}
	
	public static Stage getStageFromEvent(Event event) {
		return ((Stage) ((Node) event.getSource()).getScene().getWindow());
	}

	public static void desabilitarComponente(Node node, boolean value) {
		node.setDisable(value);
	}

	public static void zerarProgressBar(ProgressIndicator pbProgressGerador) {
		pbProgressGerador.progressProperty().unbind();
		pbProgressGerador.setProgress(0);
	}
	
	private static void saveFilePrefs(File file, Class<? extends Object> classe) {
	    Preferences prefs = Preferences.userNodeForPackage(classe);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());
	    } else {
	        prefs.remove("filePath");
	    }
	}
	
	public File loadFilePrefs(Class<? extends Object> classe) {
	    Preferences prefs = Preferences.userNodeForPackage(classe);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}
	
	public static void savePrefs(Object object) {
		Marshaller marshaller = null;
        JAXBContext context = null;
        File file = new File(String.join(File.separator, System.getProperty("user.dir"), "prefs.xml"));
        try {
            context = JAXBContext.newInstance(object.getClass());
            marshaller = context.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, file);
            
            saveFilePrefs(file, object.getClass());
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	
	public static void loadPrefs() {
		PreferencesModel preferences = null;
	    try {
	        JAXBContext context = JAXBContext.newInstance(PreferencesModel.class);
	        Unmarshaller um = context.createUnmarshaller();

	        preferences = (PreferencesModel) um.unmarshal(new File(String.join(File.separator, System.getProperty("user.dir"), "prefs.xml")));
	        
	        PrefsSingleton.getInstance();
	        PrefsSingleton.setPrefs(preferences);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
