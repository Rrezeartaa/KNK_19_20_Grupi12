package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import application.databaza.DbConnection;

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
  private DatePicker dob;
  
  private Button signButton;
  private loginman application;
  
  ResultSet resultset;
  PreparedStatement preparedStatement;
  Connection connection;
	private static Connection dbConnection;

	private final static String host = "localhost";
	private final static String dbName = "aeroporti";
	private final static String username = "root";
	private final static String password = "";

	public static Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Class.forName("org.sqlite.JDBC");
				dbConnection = DriverManager.getConnection(
						"jdbc:mysql://" + host + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true", username,
						password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dbConnection;
	}
   public login(){
      connection =getConnection();
  }
  

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
//    application= new loginman();
  }

  @FXML
  private void loginButtonClick(ActionEvent event) throws Exception {
   
	  if (event.getSource() == loginButton) {
          
          if (logIn().equals("Success")) {
              try {

                  Node node = (Node) event.getSource();
                  Stage stage = (Stage) node.getScene().getWindow();
                  stage.close();
                  Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("application/views/sample.fxml")));
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
    
      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/signup.fxml"));
      Scene scene = new Scene(parenti);
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      primaryStage.setScene(scene);
      primaryStage.show();  
  }
    private void saveData() throws NoSuchAlgorithmException {

        try {
       	 
            String st = "INSERT INTO u_serss (u_emri, u_mbiemri, u_email,u_hash) VALUES (?,?,?,?)";
          
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest((pass.getText()).getBytes());
            String encodedHash = new String(digest);
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, emri.getText());
            preparedStatement.setString(2, mbiemri.getText());
           // preparedStatement.setString(3, dob.getValue().toString());
            preparedStatement.setString(3, email.getText());
            preparedStatement.setString(4, encodedHash);   
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 @FXML
  private void signUpiButtonClick(ActionEvent event) throws Exception {
	     
		  Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/hyrja.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	       saveData();
	       
  }
 
 private String logIn() throws NoSuchAlgorithmException {
	 
     String status = "Success";
     String email = usernameField.getText();
     String password = passwordField.getText();
     
     if(email.isEmpty() || password.isEmpty()) {
         System.out.println("Empty credentials");
         status = "Error";
     } else {

    	 MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        // messageDigest.update(Encoding.UTF8.GetBytes(salt_encoded));
    	 byte[] digest = messageDigest.digest((pass.getText()).getBytes());
         String encodedHash = new String(digest);
         String sql = "SELECT * FROM u_serss Where u_email="+email+"and u_hash="+encodedHash;
         
         try {
        	        	 
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, email);
             preparedStatement.setString(2, encodedHash);
             resultset = preparedStatement.executeQuery();
             
             if (!resultset.next()) {
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
 
  final KeyCombination key= new KeyCodeCombination(KeyCode.ENTER);
  @FXML
  public void onScreenKeyPressed(KeyEvent event) throws Exception {
	  if (key.match(event)) {
	      
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/sample.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	      
	    }  
  }
}
