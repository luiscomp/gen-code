package ui.application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import ui.controller.ProjetosController;


public class MainApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		new ProjetosController().abrirTela();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
