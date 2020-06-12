package pwm.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import pwm.components.ErrorPopupComponent;
import pwm.components.FindProductComponent;
import pwm.components.PaginationComponent;
import pwm.models.Product;
import pwm.repositories.ProductRepository;
public class ProductListController extends ChildController {
  final KeyCombination keyCtrlF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
  private final int PAGE_SIZE = 10;

  private PaginationComponent paginationComponent;

  @FXML
  private TableView<Product> tableView;
  @FXML
  private TableColumn<Product, Integer> idColumn;
  @FXML
  private TableColumn<Product, String> titleColumn;
  @FXML
  private TableColumn<Product, Double> priceColumn;
  @FXML
  private TableColumn<Product, Double> qtyColumn;
  @FXML
  private ToggleButton multipleButton;
  @FXML
  private Button findButton;
  @FXML
  private Button showAllButton;
  @FXML
  private HBox paginationPane;
  @FXML
  private MenuItem viewMenuItem;
  @FXML
  private MenuItem editMenuItem;
  @FXML
  private MenuItem removeMenuItem;

