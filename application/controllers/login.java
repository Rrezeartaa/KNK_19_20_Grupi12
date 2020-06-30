package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import application.controllers.LangEnum;
import application.controllers.AppLanguage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
import javafx.scene.control.CheckMenuItem;
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

public class login extends BaseController implements Initializable {
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
            String encodedHash =digest.toString();
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
	     if(emri.getText().isEmpty() || pass.getText().isEmpty()) {
		 Alert alert = new Alert(AlertType.ERROR);
	      alert.setContentText("Shenoni emrin dhe password!");
	      alert.showAndWait();
		 
	 }
	 if(!pass.getText().equals(confpass.getText())) {
		 Alert alert = new Alert(AlertType.ERROR);
	      alert.setContentText("Fjalekalimet nuk jane te njejte!");
	      alert.showAndWait();
		 
	 }
	 else {
		  Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/hyrja.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	       saveData();}
	       
  }
 
 private String logIn() throws NoSuchAlgorithmException {
	 
     String status = "Success";
     String email = usernameField.getText();
     String password = passwordField.getText();
     
     if(email.isEmpty()) {
         System.out.println("Enter your email!");
         status = "Error";
     } else {

    	 MessageDigest mdi = MessageDigest.getInstance("SHA-256");
         byte[] digesti = mdi.digest((passwordField.getText()).getBytes());
         String encodedHashi =digesti.toString();
         String sql = "SELECT * FROM u_serss Where u_email=?";
         
         try {
        	        	 
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, email);
            // preparedStatement.setString(2, encodedHashi);
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
 @FXML
 private CheckMenuItem enCheckMenuItem;
 @FXML
 private CheckMenuItem alCheckMenuItem;
	 @Override
	  public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
	    boolean enSelected = AppLanguage.get().getLanguage() == LangEnum.EN;
	  enCheckMenuItem.setSelected(enSelected);
	alCheckMenuItem.setSelected(!enSelected);
	    
	  }
	 @FXML
	  private void onChangeLangMenuItemEnClick(ActionEvent event) {
	    enCheckMenuItem.setSelected(true);
	    alCheckMenuItem.setSelected(false);
	    changeUILanguage();
	  }
	 private BaseController childController;
	 
	  @FXML
	  private void onChangeLangMenuItemAlClick(ActionEvent event) {
	    enCheckMenuItem.setSelected(false);
	    alCheckMenuItem.setSelected(true);
	    changeUILanguage();
	  }
	  private void changeUILanguage() {
		    try {
		      LangEnum lang = enCheckMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
		      AppLanguage.get().setLanguage(lang);
		      
		      ResourceBundle langBundle = getLangBundle();
		      loadLangTexts(langBundle);
		      childController.loadLangTexts(langBundle);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		    }
		  }

	 @FXML
Text ar;
@FXML
Text emrii;
@FXML
Text passs;
	  @Override
	  public void loadLangTexts(ResourceBundle langBundle) {
	    String rez1 = langBundle.getString("rez1");
	    String rez2 = langBundle.getString("rez2");
	    String rez3 = langBundle.getString("rez3");
	    String rez4 = langBundle.getString("rez4");
	    String rez5 = langBundle.getString("rez5");
	   
	     
	    loginButton.setText(rez1);
	    signupButton.setText(rez2);
	    emrii.setText(rez3);
	    passs.setText(rez4);
	    ar.setText(rez5);
	    
	    
	  }
  final KeyCombination key= new KeyCodeCombination(KeyCode.ENTER);
  @FXML
  public void onScreenKeyPressed(KeyEvent event) throws Exception {
	  if (key.match(event)) {
	
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
  } 
