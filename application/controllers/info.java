package application.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import javafx.geometry.Pos;

public class info {	
	@FXML
	ImageView imgi=new ImageView();
	@FXML
	VBox box=new VBox();
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
	    
	      Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/pl.fxml"));
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
		    newScale.setX(imgi.getScaleX() - 2);
		    newScale.setY(imgi.getScaleY() - 2);
		    newScale.setPivotX(imgi.getScaleX());
		    newScale.setPivotY(imgi.getScaleY());
		    imgi.getTransforms().add(newScale);
	    
	  }
	  @FXML
	  private void s8(ActionEvent event) throws Exception {
	    
		  box.setStyle("-fx-background-color:red");
	    
	  }
	 
}
