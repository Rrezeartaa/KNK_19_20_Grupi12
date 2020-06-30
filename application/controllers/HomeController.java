package application.controllers;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable{

    @FXML
    private TableView<Model> tblData;

    @FXML
    private TableColumn<Model, String> emriCol;

    @FXML
    private TableColumn<Model, String> mbiemriCol;
    
    @FXML
    private TableColumn<Model, String> datelindjacol;

    @FXML
    private DatePicker fieldDob;


    @FXML
    private TableColumn<Model, String> emailCol;
    
    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtMbiemri;

    @FXML
    private TextField txtEmail;

 

    @FXML
    private Button btnAdd;

    @FXML
    void AddUserEvent(ActionEvent event) throws NoSuchAlgorithmException {

    	saveData();
    }

    
    ObservableList<Model> list;

    
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
     public HomeController() {
    	 connection = getConnection();
    	 System.out.println("Connected");
	}
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	try {
			list = FXCollections.observableArrayList();
			
			ResultSet rs = connection.createStatement().executeQuery("SELECT u_emri,u_mbiemri,datelindja,u_email FROM u_serss");
			while(rs.next()) {
				list.add(new Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				
			}
			
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}
    	
    	
    	emriCol.setCellValueFactory(new PropertyValueFactory<>("emri"));
    	mbiemriCol.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
    	datelindjacol.setCellValueFactory(new PropertyValueFactory<>("datelindja"));
    	emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	
    	
    	tblData.setItems(null);
        tblData.setItems(list);
    
    }
    
    private void saveData() throws NoSuchAlgorithmException {

        try {
       	 
            String st = "INSERT INTO u_serss (u_emri, u_mbiemri, datelindja,u_email) VALUES (?,?,?,?)";
          
            
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtEmri.getText());
            preparedStatement.setString(2, txtMbiemri.getText());
            preparedStatement.setString(3, fieldDob.getValue().toString());
            preparedStatement.setString(4, txtEmail.getText());   
            preparedStatement.executeUpdate();
	    
	    System.out.println("Te dhenat u insertuan me sukses");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	@FXML
    private void update() throws NoSuchAlgorithmException {

        try {
       	 
            String st = "UPDATE u_serss\r\n" + 
            		"SET u_email = ? WHERE u_emri=? and u_mbiemri=?";
          
            
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(2, txtEmri.getText());
            preparedStatement.setString(3, txtMbiemri.getText());
           // preparedStatement.setString(3, fieldDob.getValue().toString());
            preparedStatement.setString(1, txtEmail.getText());   
            preparedStatement.executeUpdate();
	    
	    System.out.println("Te dhenat u update me sukses");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void delete() throws NoSuchAlgorithmException {

        try {
       	 
            String st = "Delete from u_serss where u_emri=? and u_mbiemri=? and u_email=?";
          
            
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, txtEmri.getText());
            preparedStatement.setString(2, txtMbiemri.getText());
           // preparedStatement.setString(3, fieldDob.getValue().toString());
            preparedStatement.setString(3, txtEmail.getText());   
            preparedStatement.executeUpdate();
	    
	    System.out.println("Te dhenat u fshine me sukses");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
	  private void back(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/sample.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
}
    


