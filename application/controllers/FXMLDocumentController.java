package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
  
    @FXML private TextField filterField;
    @FXML private TableView<Employee> tableview;
    @FXML private TableColumn<Employee, String> Airline;
    @FXML private TableColumn<Employee, String> From;
    @FXML private TableColumn<Employee, String> Date;
    @FXML private TableColumn<Employee, String> Scheduled;
    @FXML private TableColumn<Employee, String> Status;

    private final ObservableList<Employee> dataList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
                               
        Airline.setCellValueFactory(new PropertyValueFactory<>("Airline"));       
        From.setCellValueFactory(new PropertyValueFactory<>("From"));        
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));        
        Scheduled.setCellValueFactory(new PropertyValueFactory<>("Scheduled"));        
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));       
               
        Employee emp1 = new Employee("CHAIR Airlines", "Zurich", "28 Jun", "08:05", "Arrived 08:22");
        Employee emp2 = new Employee( "EDELWEISS Air", "Zurich", "26 Jun", "08:15", "Arrived 08:38");
        Employee emp3 = new Employee( "TURKISH Airlines", "Instambul", "3 July", "08:20", "Cancelled");
        Employee emp4 = new Employee("EASYJET Europe", "Berlin", "04 July", "08:35", "Cancelled"); 
        Employee emp5 = new Employee("EUROWINGS ", "Bern", "30 Jun", "08:05", "Arrived 08:22");
        Employee emp6 = new Employee( "AIR MEDITERRAEN", "Stuttgart", "01 July", "10:15", "Arrive 11:38");
        Employee emp7 = new Employee( "AURTIAL Airlines", "Vienna", "3 July", "08:20", "Cancelled");
        Employee emp8 = new Employee("SWISS", "Oslo", "05 July", "11:35", "Cancelled");  
           
        dataList.addAll(emp1,emp2, emp3, emp4,emp5,emp6,emp7,emp8);
            FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
       
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getFrom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (employee.getScheduled().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				else if (String.valueOf(employee.getStatus()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		SortedList<Employee> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		tableview.setItems(sortedData);
	    				
         }    
    @FXML
	  private void home(ActionEvent event) throws Exception {
	    
		  Parent parenti = FXMLLoader.load(getClass().getClassLoader().getResource("application/views/sample.fxml"));
	      Scene scene = new Scene(parenti);
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      primaryStage.setScene(scene);
	      primaryStage.show();
	    
	  }
}
