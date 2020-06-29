package application.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import application.loginman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class info {	
	  @FXML
	  private void img1(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/info.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	 @FXML
	  private void img(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/s1.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	 @FXML
	  private void s2(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/s2.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	 @FXML
	  private void s3(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/s3.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	  @FXML
	  private void s4(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/s4.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
        @FXML
	  private void imggg(ActionEvent event){
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/slideshow.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	 @FXML
	  private void s5(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/map1.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	 @FXML
	  private void rez(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/reservation.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	  @FXML
	  private void s6(ActionEvent event) throws Exception {
	    
		  Scale newScale = new Scale();
		    newScale.setX(img.getScaleX() + 2);
		    newScale.setY(img.getScaleY() + 2);
		    newScale.setPivotX(img.getScaleX());
		    newScale.setPivotY(img.getScaleY());
		    img.getTransforms().add(newScale);
	    
	  }
	  @FXML
	  private void s7(ActionEvent event) throws Exception {
	    
		  Scale newScale = new Scale();
		    newScale.setX(img.getScaleX() + 2);
		    newScale.setY(img.getScaleY() + 2);
		    newScale.setPivotX(img.getScaleX());
		    newScale.setPivotY(img.getScaleY());
		    img.getTransforms().add(newScale);
	    
	  }
	  @FXML
	  private void s8(ActionEvent event) throws Exception {
	    
		  box.setStyle("-fx-background-color:red");
	    
	  }
}
