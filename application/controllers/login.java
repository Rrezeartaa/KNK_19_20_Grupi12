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

public class login implements Initializable {
  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button loginButton;
  @FXML
  private Button signupButton;
  @FXML
  private TextField emri;
  @FXML
  private TextField mbiemri;
  @FXML 
  private PasswordField pass;
  @FXML
  private PasswordField confpass;
  @FXML
  private TextField email;
  @FXML
  private Button signButton;
  private loginman application;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    application= new loginman();
  }

  private boolean login(String username, String password) {
    return application.find(username, password) != null;
  }//kjo lidhet me metoden find te klasa loginman e rregulloni me databaze 
   private boolean logini(String username, String password) {
	    return application.find(username, password) != null;
	  }//kjo lidhet me metoden find te klasa loginman e rregulloni me databaze 

  @FXML
  private void loginButtonClick(ActionEvent event) throws Exception {
    if (login(usernameField.getText(), passwordField.getText())) {
      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/signup.fxml"));
      Scene scene = new Scene(parenti);
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      primaryStage.setScene(scene);
      primaryStage.show();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setContentText("Gabim! Shenoni emrin dhe password-in edhe njehere!");
      alert.showAndWait();
    }
  }
    @FXML
  private void signUpButtonClick(ActionEvent event) throws Exception {
    
      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/signup.fxml"));
      Scene scene = new Scene(parenti);
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      primaryStage.setScene(scene);
      primaryStage.show();  
  }
 @FXML
  private void signUpiButtonClick(ActionEvent event) throws Exception {
	  if (logini(emri.getText(), pass.getText())) {	  
		  Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/hyrja.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();		  		 
     }	 
	  else {
		  Alert alert = new Alert(AlertType.ERROR);
	      alert.setContentText("Gabim! Ju lutem shenoni te gjitha te dhenat!");
	      alert.showAndWait();
	  }
  }
}
