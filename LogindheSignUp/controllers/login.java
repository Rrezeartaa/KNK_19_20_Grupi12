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

  private loginman application;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    application= new loginman();
  }

  private boolean login(String username, String password) {
    return application.find(username, password) != null;
  }

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
}
