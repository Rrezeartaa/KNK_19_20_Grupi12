package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import application.databaza.DbConnection;

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
  
  @FXML
  private DatePicker dob;
  
  private Button signButton;
  private loginman application;
  
  
  PreparedStatement preparedStatement;
  Connection connection;

  public public login(){
      connection = (Connection) DbConnection.getConnection();
  }
  

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
//    application= new loginman();
  }

  private boolean login(String username, String password) {
    return application.find(username, password) != null;
  }//kjo lidhet me metoden find te klasa loginman e rregulloni me databaze 
   private boolean logini(String username, String password) {
	    return application.find(username, password) != null;
	  }//kjo lidhet me metoden find te klasa loginman e rregulloni me databaze 

  @FXML
  private void loginButtonClick(ActionEvent event) throws Exception {
    /*if (login(usernameField.getText(), passwordField.getText())) {
      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/signup.fxml"));
      Scene scene = new Scene(parenti);
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      primaryStage.setScene(scene);
      primaryStage.show();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setContentText("Gabim! Shenoni emrin dhe password-in edhe njehere!");
      alert.showAndWait();
    }*/
	  
	  if (event.getSource() == loginButton) {
          
          if (logIn().equals("Success")) {
              try {

                  Node node = (Node) event.getSource();
                  Stage stage = (Stage) node.getScene().getWindow();
                  //stage.setMaximized(true);
                  stage.close();
                  Scene scene = new Scene(FXMLLoader.load(getClass().getResource("application/signup.fxml")));
                  stage.setScene(scene);
                  stage.show();

              } catch (IOException ex) {
                  System.err.println(ex.getMessage());
              }

          }
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
	      
	      saveData();
	      
     }	 
	  else {
		  Alert alert = new Alert(AlertType.ERROR);
	      alert.setContentText("Gabim! Ju lutem shenoni te gjitha te dhenat!");
	      alert.showAndWait();
	  }
  }
 
 private void saveData() {

     try {
    	 
         String st = "INSERT INTO users (u_emri, u_mbiemri, u_datelindja, u_email) VALUES (?,?,?,?)";
         
         preparedStatement = (PreparedStatement) connection.prepareStatement(st);
         preparedStatement.setString(1, emri.getText());
         preparedStatement.setString(2, mbiemri.getText());
         preparedStatement.setString(3, dob.getValue().toString());
         preparedStatement.setString(4, email.getText());
             
         preparedStatement.executeUpdate();

     } catch (SQLException ex) {
         System.out.println(ex.getMessage());
     }
 }
 
 private String logIn() {
	 
     String status = "Success";
     String email = usernameField.getText();
     String password = passwordField.getText();
     
     if(email.isEmpty() || password.isEmpty()) {
         System.out.println("Empty credentials");
         status = "Error";
     } else {
         
         String sql = "SELECT * FROM admins Where email = ? and password = ?";
         
         try {
        	 
             preparedStatement = con.prepareStatement(sql);
             preparedStatement.setString(1, email);
             preparedStatement.setString(2, password);
             resultSet = preparedStatement.executeQuery();
             
             if (!resultSet.next()) {
                 System.out.println("Enter Correct Email/Password");
                 status = "Error";
             } else {
                 System.out.println("Login Successful..Redirecting..");
             }
             
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             status = "Exception";
         }
     }
     
     return status;
 }
 
}
