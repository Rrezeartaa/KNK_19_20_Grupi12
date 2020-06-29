package javafxtableviewaddrows;

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
/**
 *
 * @author Cool IT Help
 */

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

        
   
                  
    //observalble list to store data
    private final ObservableList<Employee> dataList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
                               
        Airline.setCellValueFactory(new PropertyValueFactory<>("Airline"));       
        From.setCellValueFactory(new PropertyValueFactory<>("From"));        
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));        
        Scheduled.setCellValueFactory(new PropertyValueFactory<>("Scheduled"));        
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));       
        
        
        Employee emp1 = new Employee("CHAIR Airlines", "Zurich", "28 Jun", "08:05", "Arrived 08:22");
        Employee emp2 = new Employee( "EDELWEISS Air", "Zurich", "26 Jun", "08:15", "Arrive 08:38");
        
        One big advantage of using the hosted jQuery from Google:

Many users already have downloaded jQuery from Google when visiting another site. 
    As a result, it will be loaded from cache when they visit your site, which leads to faster loading time. 
    Also, most CDN's will make sure that once a user requests a file from it, it will be served from the server closest to them, which also leads to faster loading time.
    
        
    // 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getFrom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getScheduled().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getStatus()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Employee> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
	    
				
        
