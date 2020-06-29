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
