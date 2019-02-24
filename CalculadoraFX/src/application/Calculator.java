package application;
	
import application.controller.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Calculator extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/CalculadoraView.fxml"));
			Parent root = (Parent)loader.load();
			
			CalculatorController controller = (CalculatorController)loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("view/calculadoraView.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Image icon = new Image(getClass().getResourceAsStream("images/icon_calculator.png"));
			primaryStage.getIcons().add(icon);
			
			primaryStage.setTitle("Calculadora");
			
			primaryStage.show();
			primaryStage.setMinWidth(primaryStage.getWidth());
			primaryStage.setMinHeight(primaryStage.getHeight());
			
			controller.initStage(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
