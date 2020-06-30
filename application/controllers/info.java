package application.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;

public class info implements Initializable{	
	@FXML
	ImageView imgi=new ImageView();
	@FXML
	VBox box=new VBox();
	
	@FXML
    private TextField txtDestinacion1;

    @FXML
    private DatePicker txtDataNisjes1;

    @FXML
    private Button btnRezervo1;

    @FXML
    private TextField txtEmri1;

    @FXML
    private TextField txtEmri2;

    @FXML
    private TextField txtDestinacioni2;

    @FXML
    private DatePicker txtDataNisjes2;

    @FXML
    private DatePicker txtDataKthimit2;

    @FXML
    private Button btnRezervo2;
    
    @FXML 
	  private Button button;
    @FXML 
	  private Button prova;
    @FXML 
	  private Button provaa;
    @FXML 
	  private Button provaaa;
    @FXML 
	  private Button provaaaa;
    @FXML 
	  private Button provaaaaa;
    @FXML 
	  private Button provaaaaaa;
    @FXML 
	  private Button provaaaaaaa;
     @FXML
	  MenuItem logout=new MenuItem("Logout",null);
     @FXML
          AnchorPane panee;
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
  	
  	public info() {
  		connection =getConnection();
	}
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
	  private void home(ActionEvent event) throws Exception {
	Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/sample.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
    @FXML
    void rezervoButtonAction1(ActionEvent event) {
    	insertData1();
    }

    @FXML
    void rezervoButtonAction2(ActionEvent event) {
    	insertData2();  	
    }

    
    private void insertData1() {

		try {

			String query = "INSERT INTO rezervimet (emri_klientit, destinacioni, data_nisjes) VALUES (?,?,?)";

			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStatement.setString(1, txtEmri1.getText());
			preparedStatement.setString(2, txtDestinacion1.getText());
			preparedStatement.setString(3, txtDataNisjes1.getValue().toString());
			
			preparedStatement.executeUpdate();
			 Alert alert = new Alert(AlertType.INFORMATION);
		      alert.setContentText("Rekordet tuaja u regjistruan me sukses!");
		      alert.showAndWait();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
    
    
    private void insertData2() {

		try {

			String query = "INSERT INTO rezervimet (emri_klientit, destinacioni, data_nisjes, data_kthimit) VALUES (?,?,?,?)";

			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStatement.setString(1, txtEmri2.getText());
			preparedStatement.setString(2, txtDestinacioni2.getText());
			preparedStatement.setString(3, txtDataNisjes2.getValue().toString());
			preparedStatement.setString(4, txtDataKthimit2.getValue().toString());
			
			preparedStatement.executeUpdate();
			 Alert alert = new Alert(AlertType.INFORMATION);
		      alert.setContentText("Rekordet tuaja u regjistruan me sukses!");
		      alert.showAndWait();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
    
    
	@FXML
	  private VBox contentPane=new VBox();
	private BaseController childController;
	public void loadView(String screen) throws Exception {
	    FXMLLoader loader = new FXMLLoader();
	    Parent node;
	    switch (screen) {
	      case "s1":
	        loader.setLocation(getClass().getClassLoader().getResource("application/views/s1.fxml"));
	        node = loader.load();
	        break;
	     
	      case "s2":
	        loader.setLocation(getClass().getClassLoader().getResource("application/views/s2.fxml"));
	        node = loader.load();
	        break;
	      case "s3":
	        loader.setLocation(getClass().getClassLoader().getResource("application/views/s3.fxml"));
	        node = loader.load();
	        break;
	      case "s4":
		        loader.setLocation(getClass().getClassLoader().getResource("application/views/s4.fxml"));
		        node = loader.load();
		        break;
	      case "map1":
		        loader.setLocation(getClass().getClassLoader().getResource("application/views/map1.fxml"));
		        node = loader.load();
		        break;
	      default:
	        throw new Exception("ERR_SCREEN_NOT_FOUND");
	    }

	   ChildController controller = loader.getController();
	    loadView(screen, node, controller);
	  }
	public void loadView(String screen, Parent node, ChildController controller) throws Exception {
	   // controller.setParentController(this);
	    FXMLLoader loader = new FXMLLoader();
//	    this.childController = controller;

	    contentPane.getChildren().clear();
	    contentPane.getChildren().add(node);
	    VBox.setVgrow(node, Priority.ALWAYS);

	    switch (screen) {
	    case "s1":
	    	contentPane.setAlignment(Pos.TOP_CENTER);
	        break;	     
	      case "s2":
	    	  contentPane.setAlignment(Pos.TOP_CENTER);
	        break;
	      case "s3":
	    	  contentPane.setAlignment(Pos.TOP_CENTER);
	        break;
	      case "s4":
	    	  contentPane.setAlignment(Pos.TOP_CENTER);
		        break;
	      case "map1":
	    	  contentPane.setAlignment(Pos.TOP_CENTER);
		        break;
	      default:
	        throw new Exception("ERR_SCREEN_NOT_FOUND");
	    }
	  }

	  @FXML
	  private void img1(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/info.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	  @FXML
	  private void img(ActionEvent event) throws Exception {
	    
		  this.loadView("s1");
	    
	  }
	  @FXML
	  private void s2(ActionEvent event) throws Exception {
	    
		  this.loadView("s2");
	    
	  }
	  @FXML
	  private void s3(ActionEvent event) throws Exception {
	    
		  this.loadView("s3");
	    
	  }
	  @FXML
	  private void s4(ActionEvent event) throws Exception {
	    
		  this.loadView("s4");
	    
	  }
	  @FXML
	  private void imggg(ActionEvent event) throws Exception {
	    
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
	  private void rezi(ActionEvent event) throws Exception {
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/FXMLDocument.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
	  @FXML
	  private void s6(ActionEvent event) throws Exception {
	    
		  Scale newScale = new Scale();
		    newScale.setX(imgi.getScaleX() + 2);
		    newScale.setY(imgi.getScaleY() + 2);
		    newScale.setPivotX(imgi.getScaleX());
		    newScale.setPivotY(imgi.getScaleY());
		    imgi.getTransforms().add(newScale);
	    
	  }
	  @FXML
	  private void s7(ActionEvent event) throws Exception {
	    
		  Scale newScale = new Scale();
		    newScale.setX(imgi.getScaleX() - 0.5);
		    newScale.setY(imgi.getScaleY() - 0.5);
		    newScale.setPivotX(imgi.getScaleX());
		    newScale.setPivotY(imgi.getScaleY());
		    imgi.getTransforms().add(newScale);
	    
	  }
	  @FXML
	  private void s8(ActionEvent event) throws Exception {
	    
		  box.setStyle("-fx-background-color:red");
	    
	  }
	@FXML
	 Label label=new Label();
	  @FXML
	  private void text(MouseEvent event) throws Exception {
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		  
		  label.setText(dtf.format(now));
	    
	  }
	   @FXML
	  private void logout(ActionEvent event) throws Exception {
		  Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/hyrja.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage)panee.getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	  }
	@FXML
	private void help(ActionEvent event) throws Exception {
              Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/help.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = new Stage(); 
	      primaryStage.setScene(scene);
	      primaryStage.show();
	  }
	
	final KeyCombination key = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY);
	  @FXML
	  public void out(KeyEvent event) throws Exception {
		  if (key.match(event)) {

	                  Node node = (Node) event.getSource();
	                  Stage stage = (Stage) node.getScene().getWindow();
	                  stage.close();
	                  Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("application/views/hyrja.fxml")));
	                  stage.setScene(scene);
	                  stage.show();
      
		    }  
		  }
	 @FXML
	  public void set(ActionEvent event) {
		  
		  prova.setText("Sllajdi");
		  provaa.setText("Informacionet");
		  provaaa.setText("Sllajdi");
		  provaaaa.setText("Relacionet publike");
		  provaaaaa.setText("Ankesat");
		  provaaaaaa.setText("Lokacioni");
		  provaaaaaaa.setText("Faqja kryesore");
		  
		  
	  }
	  @FXML
	  public void seti(ActionEvent event) {
		  prova.setText("SlideShow");
		  provaa.setText("Information Desk");
		  provaaa.setText("Lost and Found");
		  provaaaa.setText("Public Relations");
		  provaaaaa.setText("Complaints Handling");
		  provaaaaaa.setText("Location");
		  provaaaaaaa.setText("Home");
		
		  
	  }
}
