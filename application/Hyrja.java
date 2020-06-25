package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Hyrja extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {	  
   
    Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/hyrja.fxml"));
    Scene scene = new Scene(parenti);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
	    Application.launch(args);
	  }
}
